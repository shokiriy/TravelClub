package uz.kruz.travelclub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    //
    private Long id;

    @NotNull(message = "Club ID must not be null")
    private Long clubId;

    @NotBlank(message = "Board name is required")
    @Size(min = 3, max = 100, message = "Board name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Admin email is required")
    @Email(message = "Admin email must be a valid email address")
    private String adminEmail;

    private LocalDateTime createDate;
}