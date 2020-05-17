package pl.coderslab.controller.REST;

import org.springframework.beans.factory.annotation.Autowired;
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
import pl.coderslab.dto.TrainingDto;
import pl.coderslab.facade.TrainingFacade;

import javax.validation.Valid;

@RequestMapping("/api/advice/{advice_id}/training")
@RestController
public class TrainingRestController {

    private TrainingFacade trainingFacade;

    @Autowired
    public TrainingRestController(TrainingFacade trainingFacade) {
        this.trainingFacade = trainingFacade;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createAdvice(@PathVariable Long advice_id, @RequestBody @Valid TrainingDto newTraining) {
        return trainingFacade.createTraining(newTraining, advice_id);
    }

    @GetMapping("/")
    public TrainingDto getByAdviceId(@PathVariable Long advice_id) {
        return trainingFacade.getById(advice_id);
    }

    @PutMapping("/")
    public TrainingDto updateTraining(@RequestBody @Valid TrainingDto training, @PathVariable Long advice_id) {
        return trainingFacade.updateTraining(training, advice_id);
    }

    @DeleteMapping("/")
    public void deleteTraining(@PathVariable Long advice_id) {
        trainingFacade.deleteTraining(advice_id);
    }
}
