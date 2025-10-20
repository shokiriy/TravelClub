package uz.kruz.travelclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kruz.travelclub.domain.SocialBoard;
import uz.kruz.travelclub.domain.TravelClub;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<SocialBoard, Long> {
    //
    List<SocialBoard> findByName(String name);
    Optional<SocialBoard> findByClub(TravelClub club);
    Optional<SocialBoard> findByClubId(Long clubId);
    List<SocialBoard> findByAdminEmail(String adminEmail);
}
