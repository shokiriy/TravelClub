package uz.kruz.travelclub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelClubDto {
    //
    private Long id;
    private Long boardId;

    @NotBlank(message = "Club name must not be blank")
    @Size(min = 3, max = 100, message = "Club name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Club intro must not be blank")
    @Size(min = 10, max = 255, message = "Club intro must be between 10 and 255 characters")
    private String intro;

    private LocalDateTime foundationDay;

    private List<MembershipDto> membershipList;
}