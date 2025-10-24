package uz.kruz.travelclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kruz.travelclub.domain.CommunityMember;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<CommunityMember, Long> {
    //
    Optional<CommunityMember> findByEmail(String email);
    List<CommunityMember> findByName(String name);
    boolean existsByPhoneNumber(String phoneNumber);
}
