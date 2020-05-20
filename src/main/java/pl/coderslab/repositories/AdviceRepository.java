package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Advice;

import java.util.List;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {

    @Query("SELECT id FROM advices")
    List<Long> getAllIds();
}
