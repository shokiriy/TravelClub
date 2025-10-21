package uz.kruz.travelclub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kruz.travelclub.domain.RoleInClub;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDto {
    //
    private Long id;

    private Long memberId;
    private Long clubId;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Member email is required")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String memberEmail;

    private String memberName;

    @NotNull(message = "Role is required")
    private RoleInClub role;

    private LocalDateTime joinDate;
}
