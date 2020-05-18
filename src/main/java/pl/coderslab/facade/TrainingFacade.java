package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.TrainingDto;
import pl.coderslab.exception.AdviceNotFoundException;
import pl.coderslab.exception.TrainingNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.model.Question;
import pl.coderslab.model.Training;
import pl.coderslab.service.AdviceService;
import pl.coderslab.service.QuestionService;
import pl.coderslab.service.TrainingService;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingFacade {

    private AdviceService adviceService;
    private TrainingService trainingService;
    private QuestionService questionService;
    private ModelMapper modelMapper;

    @Autowired
    public TrainingFacade(AdviceService adviceService, TrainingService trainingService, QuestionService questionService, ModelMapper modelMapper) {
        this.adviceService = adviceService;
        this.trainingService = trainingService;
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    public TrainingDto getById(Long advice_id) {
        Training training = trainingService.findById(advice_id).orElseThrow(() -> new TrainingNotFoundException(advice_id));
        return convertToTrainingDto(training);
    }

    public TrainingDto createTraining(TrainingDto newTraining, Long advice_id) {
        return convertToTrainingDto(trainingService.save(convertToTraining(newTraining, advice_id)));
    }

    public TrainingDto updateTraining(TrainingDto trainingDto, Long advice_id) {
        trainingService.findById(advice_id).orElseThrow(() -> new TrainingNotFoundException(advice_id));
        return convertToTrainingDto(trainingService.save(convertToTraining(trainingDto, advice_id)));
    }

    public void deleteTraining(Long advice_id) {
        Training training = trainingService.findById(advice_id).orElseThrow(() -> new TrainingNotFoundException(advice_id));
        training.getAdvice().setTraining(null);
        trainingService.delete(training);
    }

    private TrainingDto convertToTrainingDto(Training training) {
        return modelMapper.map(training, TrainingDto.class);
    }
    private Training convertToTraining(TrainingDto trainingDto, Long training_id) {
        Advice advice = adviceService.findById(training_id).orElseThrow(() -> new AdviceNotFoundException(training_id));
        Training training = trainingService.findById(training_id).orElse(new Training(advice));
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
