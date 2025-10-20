package uz.kruz.travelclub.domain;

import jakarta.persistence.*;
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
public class ClubMembership {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String memberEmail;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleInClub role;
    @CreationTimestamp
    private LocalDateTime joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private TravelClub club;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private CommunityMember member;
}