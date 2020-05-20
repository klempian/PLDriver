package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
