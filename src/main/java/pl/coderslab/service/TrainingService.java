package pl.coderslab.service;

import pl.coderslab.model.Training;

import java.util.Optional;

public interface TrainingService {

    Training save(Training training);

    Optional<Training> findById(Long id);

    void delete(Training training);
}
