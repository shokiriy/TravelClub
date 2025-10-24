package uz.kruz.travelclub.service.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.TravelClubDto;
import uz.kruz.travelclub.repository.ClubRepository;
import uz.kruz.travelclub.service.ClubService;
import uz.kruz.travelclub.service.mapper.TravelClubMapper;
import uz.kruz.travelclub.util.ClubDuplicationException;
import uz.kruz.travelclub.util.NoSuchClubException;

@Service
@RequiredArgsConstructor
public class ClubServiceLogic implements ClubService {
    //
    private final ClubRepository clubRepository;
    private final TravelClubMapper clubMapper;

    @Override
    public void register(TravelClubDto clubDto) {
        //
        clubRepository.findByName(clubDto.getName())
                .ifPresent(club -> {
                    throw new ClubDuplicationException("Club already exists with name --> " + clubDto.getName());
                });

        TravelClub club = clubMapper.toEntity(clubDto);

        clubRepository.save(club);
    }

    @Override
    public TravelClubDto findClub(Long clubId) {
        //
        TravelClub foundClub = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + clubId));

        return clubMapper.toDto(foundClub);
    }

    @Override
    public TravelClubDto findClubByName(String name) {
        //
        TravelClub club = clubRepository.findByName(name)
                .orElseThrow(() -> new NoSuchClubException("No such club with name --> " + name));

        return clubMapper.toDto(club);
    }

    @Override
    public void modify(TravelClubDto clubDto) {
        //
        TravelClub targetClub = clubRepository.findById(clubDto.getId())
                .orElseThrow(() -> new NoSuchClubException("No such club with id --> " + clubDto.getId()));

        if (clubDto.getName() != null && !clubDto.getName().isEmpty()) {
            if(!targetClub.getName().equals(clubDto.getName())){
                boolean exists = clubRepository.existsByName(clubDto.getName());
                if (exists) {
                    throw new ClubDuplicationException("Club already exists with name --> " + clubDto.getName());
                }
            }
            targetClub.setName(clubDto.getName());
        }
        if (clubDto.getIntro() != null && !clubDto.getIntro().isEmpty()) {
            targetClub.setIntro(clubDto.getIntro());
        }

        clubRepository.save(targetClub);
    }

    @Override
    public void remove(Long clubId) {
        //
        if (!clubRepository.existsById(clubId)) {
            throw new NoSuchClubException("No such club with id --> " + clubId);
        }

        clubRepository.deleteById(clubId);
    }
}