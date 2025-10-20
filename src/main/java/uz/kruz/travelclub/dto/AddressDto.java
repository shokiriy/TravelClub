package uz.kruz.travelclub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kruz.travelclub.domain.AddressType;
import uz.kruz.travelclub.domain.CommunityMember;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    //
    private Long id;
    private String country;
    private String streetAddress;
    private AddressType addressType;
    private String zipCode;
    private String zipAddress;
    private CommunityMember member;
}
