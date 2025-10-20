package uz.kruz.travelclub.dto;

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
    private String memberEmail;
    private String memberName;
    private RoleInClub role;
    private LocalDateTime joinDate;
}
