package pl.coderslab.service;

import pl.coderslab.model.Question;

import java.util.Optional;

public interface QuestionService {

    Question save(Question question);

    Optional<Question> findById(Long id);

    void delete(Question question);
}
