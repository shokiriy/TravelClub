package uz.kruz.travelclub.service.mapper;

import org.mapstruct.Mapper;
import uz.kruz.travelclub.domain.Address;
import uz.kruz.travelclub.dto.AddressDto;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    //
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}
