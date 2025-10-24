package uz.kruz.travelclub.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingDto {
    //
    private Long id;

    private Long boardId;

    @NotBlank(message = "Title should not be blank!")
    @Size(min = 3, max = 100, message = "The length of title must be between 3 and 100 characters!")
    private String title;

    @NotBlank(message = "Writer's email should not be blank!")
    @Email(message = "The format of email is wrong!")
    private String writerEmail;

    @NotBlank(message = "Posting contents should not be blank!")
    @Size(min = 10, max = 2000, message = "The length of contents must be between 10 and 2000!")
    private String contents;

    private LocalDateTime writtenDate;

    @Min(value = 0, message = "Read count must be positive number!")
    private int readCount;
}