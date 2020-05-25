package pl.coderslab.controller.REST;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.converter.TrainingConverter;
import pl.coderslab.dto.TrainingDto;
import pl.coderslab.exception.TrainingNotFoundException;
import pl.coderslab.model.Training;
import pl.coderslab.service.TrainingService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/advice/{adviceId}/training")
@RestController
public class TrainingController {

    private final TrainingConverter trainingConverter;
    private final TrainingService trainingService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto create(@PathVariable Long adviceId, @RequestBody @Valid TrainingDto newTraining) {
        return trainingConverter.convertToTrainingDto(trainingService.save(trainingConverter.convertToTraining(newTraining, adviceId)));
    }

    @GetMapping("/")
    public TrainingDto getByAdviceId(@PathVariable Long adviceId) {
        Training training = trainingService.findById(adviceId).orElseThrow(() -> new TrainingNotFoundException(adviceId));
        return trainingConverter.convertToTrainingDto(training);
    }

    @PutMapping("/")
    public TrainingDto update(@RequestBody @Valid TrainingDto trainingDto, @PathVariable Long adviceId) {
        trainingService.findById(adviceId).orElseThrow(() -> new TrainingNotFoundException(adviceId));
        return trainingConverter.convertToTrainingDto(trainingService.save(trainingConverter.convertToTraining(trainingDto, adviceId)));
    }

    @DeleteMapping("/")
    public void delete(@PathVariable Long adviceId) {
        Training training = trainingService.findById(adviceId).orElseThrow(() -> new TrainingNotFoundException(adviceId));
        training.getAdvice().setTraining(null);
        trainingService.delete(training);
    }
}
