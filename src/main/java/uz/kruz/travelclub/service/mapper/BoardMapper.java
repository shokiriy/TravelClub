package uz.kruz.travelclub.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.kruz.travelclub.domain.SocialBoard;
import uz.kruz.travelclub.domain.TravelClub;
import uz.kruz.travelclub.dto.BoardDto;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    @Mapping(target = "postings", ignore = true)
    SocialBoard toEntity(BoardDto dto, TravelClub travelClub);

    @Mapping(target = "clubId", source = "club.id")
    BoardDto toDto(SocialBoard entity);
}
