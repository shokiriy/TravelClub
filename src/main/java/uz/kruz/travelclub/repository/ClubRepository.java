package uz.kruz.travelclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kruz.travelclub.domain.TravelClub;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<TravelClub, Long> {
    //
    Optional<TravelClub> findByName(String clubName);
    boolean existsByName(String clubName);
}
