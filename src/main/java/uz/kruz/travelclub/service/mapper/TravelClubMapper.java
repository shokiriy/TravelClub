package uz.kruz.travelclub.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.TravelClubDto;
@Mapper(componentModel = "spring", uses = { MembershipMapper.class })
public interface TravelClubMapper {

    @Mapping(target = "board", ignore = true)
    @Mapping(target = "membershipList", ignore = true)
    TravelClub toEntity(TravelClubDto clubDto);

    @Mapping(target = "boardId", expression = "java(club.getBoard() != null ? club.getBoard().getId() : null)")
    TravelClubDto toDto(TravelClub club);
}
