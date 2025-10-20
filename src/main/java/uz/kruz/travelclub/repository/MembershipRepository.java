package uz.kruz.travelclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kruz.travelclub.domain.ClubMembership;
import uz.kruz.travelclub.domain.RoleInClub;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<ClubMembership, Long> {
    //
    Optional<ClubMembership> findByMemberEmail(String memberEmail);
    Optional<ClubMembership> findByClubIdAndMemberEmail(Long clubId, String memberEmail);

}
