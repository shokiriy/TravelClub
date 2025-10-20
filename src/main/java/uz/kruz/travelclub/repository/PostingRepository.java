package uz.kruz.travelclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kruz.travelclub.domain.Posting;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {
    //
    List<Posting> findAllByBoardId(Long boardId);
}
