package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Question;
import pl.coderslab.repositories.QuestionRepository;
import pl.coderslab.service.QuestionService;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return id != null ? questionRepository.findById(id) : Optional.empty();
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }
}
