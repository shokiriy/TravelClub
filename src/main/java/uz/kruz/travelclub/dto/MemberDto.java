package uz.kruz.travelclub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kruz.travelclub.domain.Address;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    //
    private Long id;

    @Email(message = "Email format is invalid")
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Nickname is required")
    @Size(min = 2, max = 30, message = "Nickname must be between 2 and 30 characters")
    private String nickName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+]{7,15}$", message = "Phone number must be numeric and 7 to 15 digits long")
    private String phoneNumber;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDay;

    private List<AddressDto> addresses;
    private List<MembershipDto> membershipList;
}
