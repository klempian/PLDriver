package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.TrainingDto;
import pl.coderslab.exception.AdviceNotFoundException;
import pl.coderslab.exception.TrainingNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.model.Training;
import pl.coderslab.service.AdviceService;
import pl.coderslab.service.TrainingService;

@Component
public class TrainingFacade {

    private AdviceService adviceService;
    private TrainingService trainingService;
    private ModelMapper modelMapper;

    @Autowired
    public TrainingFacade(AdviceService adviceService, TrainingService trainingService, ModelMapper modelMapper) {
        this.adviceService = adviceService;
        this.trainingService = trainingService;
        this.modelMapper = modelMapper;
    }

    public TrainingDto getById(Long training_id) {
        Training training = trainingService.findById(training_id).orElseThrow(() -> new TrainingNotFoundException(training_id));
        return convertToTrainingDto(training);
    }

    public TrainingDto createTraining(TrainingDto newTraining, Long advice_id) {
        return convertToTrainingDto(trainingService.save(convertToTraining(newTraining, advice_id)));
    }

    public TrainingDto updateTraining(TrainingDto trainingDto, Long training_id) {
        trainingService.findById(training_id).orElseThrow(() -> new TrainingNotFoundException(training_id));
        return convertToTrainingDto(trainingService.save(convertToTraining(trainingDto, training_id)));
    }

    public void deleteTraining(Long training_id) {
        Training training = trainingService.findById(training_id).orElseThrow(() -> new TrainingNotFoundException(training_id));
        training.getAdvice().setTraining(null);
        trainingService.delete(training);
    }

    private TrainingDto convertToTrainingDto(Training training) {
        return modelMapper.map(training, TrainingDto.class);
    }
    private Training convertToTraining(TrainingDto trainingDto, Long training_id) {
        Advice advice = adviceService.findById(training_id).orElseThrow(() -> new AdviceNotFoundException(training_id));
        return advice.getTraining() == null ? new Training(advice) : advice.getTraining();
    }
}
