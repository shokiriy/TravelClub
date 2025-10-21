package uz.kruz.travelclub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Member ID is required")
    private Long memberId;

    @NotBlank(message = "Country name is required")
    @Size(min = 2, max = 50, message = "Country name must be between 2 and 50 characters")
    private String country;

    @NotBlank(message = "Street address is required")
    @Size(min = 5, max = 100, message = "Street address must be between 5 and 100 characters")
    private String streetAddress;

    @NotNull(message = "Address type is required")
    private AddressType addressType;

    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^[0-9]{4,10}$", message = "Zip code must be numeric and between 4 to 10 digits")
    private String zipCode;

    @Size(max = 100, message = "Zip address must not exceed 100 characters")
    private String zipAddress;
}