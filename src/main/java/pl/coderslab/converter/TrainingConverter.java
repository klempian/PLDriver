package pl.coderslab.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.TrainingDto;
import pl.coderslab.dto.TrainingNewDto;
import pl.coderslab.exception.ResourceNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.model.Question;
import pl.coderslab.model.Training;
import pl.coderslab.service.AdviceService;
import pl.coderslab.service.QuestionService;
import pl.coderslab.service.TrainingService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TrainingConverter {

    private final ModelMapper modelMapper;
    private final AdviceService adviceService;
    private final TrainingService trainingService;
    private final QuestionService questionService;

    public TrainingDto convertToTrainingDto(Training training) {
        return modelMapper.map(training, TrainingDto.class);
    }

    public Training convertToTraining(TrainingNewDto newTraining, Long adviceId) {
        Advice advice = adviceService.findById(adviceId).orElseThrow(() -> new ResourceNotFoundException("Advice", adviceId));
        Training training = new Training(advice);
        List<Question> questions = new ArrayList<>();
        newTraining.getQuestions().forEach(
                questionNewDto -> {
                    Question question = new Question();
                    question.setTitle(questionNewDto.getTitle());
                    questions.add(question);
                }
        );
        training.getQuestions().addAll(questions);
        return training;
    }

    public Training convertToTraining(TrainingDto trainingDto, Long trainingId) {
        Training training = trainingService.findById(trainingId).orElseThrow(() -> new ResourceNotFoundException("Training", trainingId));
        List<Question> questions = new ArrayList<>();
        trainingDto.getQuestions().forEach(
                questionDto -> {
                    Question question = questionService.findById(questionDto.getId()).orElse(new Question());
                    question.setTitle(questionDto.getTitle());
                    questions.add(question);
                }
        );
        training.getQuestions().clear();
        training.getQuestions().addAll(questions);
        return training;
    }
}
