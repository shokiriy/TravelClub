package uz.kruz.travelclub.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialBoard {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    @NotBlank(message = "Board name must not be blank")
    @Size(min = 3, max = 100, message = "Board name should be between 3 and 100 characters")
    private String name;

    @Column(length = 100)
    @Email(message = "Admin email must be a valid email address")
    private String adminEmail;

    @CreationTimestamp
    private LocalDateTime createDate;

    @OneToOne
    @JoinColumn(name = "club_id", unique = true, nullable = false)
    @NotNull(message = "SocialBoard must be linked to a TravelClub")
    private TravelClub club;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Posting> postings = new ArrayList<>();
}