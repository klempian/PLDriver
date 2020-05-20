package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.WeeklyAdvice;

import java.util.List;

@Repository
public interface WeeklyAdviceRepository extends JpaRepository<WeeklyAdvice, Long> {

    @Query("SELECT advice_id FROM weekly_advices")
    List<Long> getAllAdviceIds();

    WeeklyAdvice findFirstByOrderByIdDesc();

    @Modifying
    @Query(value = "TRUNCATE TABLE weekly_advices", nativeQuery = true)
    void truncateTable();

}
