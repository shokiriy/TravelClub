package uz.kruz.travelclub.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "travel_club")
public class TravelClub {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10, max = 255, message = "Intro must be between 10 and 255 characters")
    private String intro;

    @Column(nullable = false)
    private LocalDateTime foundationDay;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private SocialBoard board;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubMembership> membershipList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (foundationDay == null) {
            foundationDay = LocalDateTime.now();
        }
    }
}