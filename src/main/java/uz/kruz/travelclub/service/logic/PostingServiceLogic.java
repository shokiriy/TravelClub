package uz.kruz.travelclub.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kruz.travelclub.domain.Posting;
import uz.kruz.travelclub.domain.SocialBoard;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.PostingDto;
import uz.kruz.travelclub.repository.BoardRepository;
import uz.kruz.travelclub.repository.ClubRepository;
import uz.kruz.travelclub.repository.PostingRepository;
import uz.kruz.travelclub.service.PostingService;
import uz.kruz.travelclub.service.mapper.PostingMapper;
import uz.kruz.travelclub.util.NoSuchBoardException;
import uz.kruz.travelclub.util.NoSuchClubException;
import uz.kruz.travelclub.util.NoSuchPostingException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceLogic implements PostingService {
    //
    private final PostingRepository postingRepository;
    private final BoardRepository boardRepository;
    private final ClubRepository clubRepository;
    private final MembershipServiceLogic membershipServiceLogic;
    private final PostingMapper postingMapper;

    @Override
    public String register(Long boardId, PostingDto postingDto) {
        //
        SocialBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        TravelClub club = clubRepository.findById(board.getClub().getId())
                .orElseThrow(() -> new NoSuchClubException("No such club for board Id --> " + boardId));

        membershipServiceLogic.getMembershipBy(club.getId(), postingDto.getWriterEmail());

        Posting posting = postingRepository.save(postingMapper.toEntity(postingDto, board));

        return posting.getId().toString();
    }

    @Override
    public PostingDto find(Long postingId) {
        //
        Posting foundPosting = postingRepository.findById(postingId)
                .orElseThrow(() -> new NoSuchPostingException("No such posting with id --> " + postingId));

        foundPosting.setReadCount(foundPosting.getReadCount() + 1);
        postingRepository.save(foundPosting);

        return postingMapper.toDto(foundPosting);
    }

    @Override
    public List<PostingDto> findByBoardId(Long boardId) {
        //
        boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        return postingRepository.findAllByBoardId(boardId).stream()
                .map(posting -> postingMapper.toDto(posting))
                .toList();
    }

    @Override
    public void modify(PostingDto postingDto) {
        //
        Posting targetPosting = postingRepository.findById(postingDto.getId())
                .orElseThrow(() -> new NoSuchPostingException("No such posting with id --> " + postingDto.getId()));

        if (postingDto.getTitle() != null && !postingDto.getTitle().isEmpty()) {
            targetPosting.setTitle(postingDto.getTitle());
        }
        if (postingDto.getContents() != null && !postingDto.getContents().isEmpty()) {
            targetPosting.setContents(postingDto.getContents());
        }

        postingRepository.save(targetPosting);
    }

    @Override
    public void remove(Long postingId) {
        //
        if (!postingRepository.existsById(postingId)) {
            throw new NoSuchPostingException("No such posting with id --> " + postingId);
        }

        postingRepository.deleteById(postingId);
    }

    @Override
    public void removeAllIn(Long boardId) {
        //
        boardRepository.findById(boardId)
                        .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        postingRepository.findAllByBoardId(boardId)
                .forEach(posting -> postingRepository.deleteById(posting.getId()));
    }
}