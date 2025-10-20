package uz.kruz.travelclub.dto;

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
    private String email;
    private String name;
    private String nickName;
    private String phoneNumber;
    private LocalDate birthDay;

    private List<Address> addresses;
    private List<MembershipDto> membershipList;
}
