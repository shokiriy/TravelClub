package uz.kruz.travelclub.service.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.TravelClubDto;
@Mapper(componentModel = "spring", uses = { MembershipMapper.class })
public interface TravelClubMapper {

    TravelClub toEntity(TravelClubDto clubDto);

    @Mapping(target = "boardId", expression = "java(club.getBoard() != null ? club.getBoard().getId() : null)")
    TravelClubDto toDto(TravelClub club);
}
//@Component
//@RequiredArgsConstructor
//public class TravelClubMapper {
//    //
//    private final MembershipMapper membershipMapper;
//
//    public TravelClub toEntity(TravelClubDto clubDto) {
//        //
//        return TravelClub.builder()
//                .id(clubDto.getId())
//                .name(clubDto.getName())
//                .intro(clubDto.getIntro())
//                .foundationDay(clubDto.getFoundationDay())
//                .build();
//    }
//
//    public TravelClubDto toDto(TravelClub club) {
//        //
//        if (club == null) {
//            return null;
//        }
//
//        return TravelClubDto.builder()
//                .id(club.getId())
//                .boardId(club.getBoard() != null ? club.getBoard().getId() : null)
//                .name(club.getName())
//                .intro(club.getIntro())
//                .foundationDay(club.getFoundationDay())
//                .membershipList(
//                        club.getMembershipList() != null ? club.getMembershipList()
//                                .stream()
//                                .map(m -> membershipMapper.toDto(m))
//                                .toList()
//                                : null
//                )
//                .build();
//    }
//}
