package uz.kruz.travelclub.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kruz.travelclub.domain.SocialBoard;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.BoardDto;
import uz.kruz.travelclub.repository.BoardRepository;
import uz.kruz.travelclub.repository.MembershipRepository;
import uz.kruz.travelclub.repository.ClubRepository;
import uz.kruz.travelclub.service.BoardService;
import uz.kruz.travelclub.util.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceLogic implements BoardService {
    //
    private final BoardRepository boardRepository;
    private final ClubRepository clubRepository;
    private final MembershipRepository membershipRepository;

    @Override
    public String register(BoardDto boardDto) {
        //
        TravelClub club = clubRepository.findById(boardDto.getClubId())
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + boardDto.getClubId()));

        boardRepository.findByClubId(boardDto.getClubId())
                .ifPresent(foundClub -> {
                    throw new ClubDuplicationException("Board already exists with club id --> " + boardDto.getClubId());
                });


        membershipRepository.findByMemberEmail(boardDto.getAdminEmail())
                .orElseThrow(() -> new NoSuchMemberException("No such member with admin email --> " + boardDto.getAdminEmail()));


        SocialBoard board = SocialBoard.builder()
                .name(boardDto.getName())
                .adminEmail(boardDto.getAdminEmail())
                .club(club)
                .build();

        boardRepository.save(board);
        club.setBoard(board);
        clubRepository.save(club);

        return board.getId().toString();
    }

    @Override
    public BoardDto find(Long boardId) {
        //
        SocialBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        return BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .adminEmail(board.getAdminEmail())
                .clubId(board.getId())
                .createDate(board.getCreateDate())
                .build();
    }

    @Override
    public List<BoardDto> findByName(String boardName) {
        //
        List<SocialBoard> boards = boardRepository.findByName(boardName);

        if (boards.isEmpty()) {
            throw new NoSuchBoardException("No such board with name --> " + boardName);
        }

        return boards.stream()
                .map(board -> BoardDto.builder()
                        .id(board.getId())
                        .name(board.getName())
                        .adminEmail(board.getAdminEmail())
                        .clubId(board.getId())
                        .createDate(board.getCreateDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        //
        TravelClub foundClub = clubRepository.findByName(clubName)
                .orElseThrow(() -> new NoSuchClubException("No such club with name --> " + clubName));

        SocialBoard foundBoard = boardRepository.findByClub(foundClub)
                .orElseThrow(() -> new NoSuchBoardException("No such board with clubName --> " + clubName));

        return BoardDto.builder()
                .id(foundBoard.getId())
                .name(foundBoard.getName())
                .adminEmail(foundBoard.getAdminEmail())
                .clubId(foundBoard.getClub().getId())
                .createDate(foundBoard.getCreateDate())
                .build();
    }

    @Override
    public void modify(BoardDto boardDto) {
        //
        SocialBoard targetBoard = boardRepository.findByClubId(boardDto.getClubId())
                .orElseThrow(() -> new NoSuchBoardException("No such board with club id --> " + boardDto.getClubId()));

        if (!boardDto.getName().isEmpty()) {
            targetBoard.setName(boardDto.getName());
        }
        if (!boardDto.getAdminEmail().isEmpty()) {
            membershipRepository.findByMemberEmail(boardDto.getAdminEmail())
                    .orElseThrow(() -> new NoSuchMembershipException("No such membership with admin email --> " + boardDto.getAdminEmail()));
            targetBoard.setAdminEmail(boardDto.getAdminEmail());
        }

        boardRepository.save(targetBoard);
    }

    @Override
    public void remove(Long boardId) {
        //
        SocialBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchBoardException("No such board with id --> " + boardId));

        TravelClub club = board.getClub();
        club.setBoard(null);

        boardRepository.deleteById(boardId);
    }
}