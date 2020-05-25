package pl.coderslab.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.TrainingDto;
import pl.coderslab.exception.AdviceNotFoundException;
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

    public Training convertToTraining(TrainingDto trainingDto, Long trainingId) {
        Advice advice = adviceService.findById(trainingId).orElseThrow(() -> new AdviceNotFoundException(trainingId));
        Training training = trainingService.findById(trainingId).orElse(new Training(advice));
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
