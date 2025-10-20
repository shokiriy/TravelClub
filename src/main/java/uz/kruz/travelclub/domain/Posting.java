package uz.kruz.travelclub.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posting {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title should not be blank!")
    @Size(min = 3, max = 100, message = "The length of title must be between 3 and 100 characters!")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Writer's email should not be blank!")
    @Email(message = "The format of email is wrong!")
    @Column(nullable = false)
    private String writerEmail;

    @NotBlank(message = "Posting contents should not be blank!")
    @Size(min = 10, max = 2000, message = "The length of contents must be between 10 and 2000!")
    @Column(nullable = false, length = 2000)
    private String contents;

    @CreationTimestamp
    private LocalDateTime writtenDate;

    @Min(value = 0, message = "Read count must be positive number!")
    @Column(nullable = false)
    private int readCount = 0;

    @NotNull(message = "Board should not be blank!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private SocialBoard board;
}