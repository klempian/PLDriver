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

@RequestMapping("/api/advice/{adviceId}/training")
@RestController
public class TrainingController {

    private TrainingFacade trainingFacade;

    @Autowired
    public TrainingController(TrainingFacade trainingFacade) {
        this.trainingFacade = trainingFacade;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@PathVariable Long adviceId, @RequestBody @Valid TrainingDto newTraining) {
        return trainingFacade.createTraining(newTraining, adviceId);
    }

    @GetMapping("/")
    public TrainingDto getByAdviceId(@PathVariable Long adviceId) {
        return trainingFacade.getById(adviceId);
    }

    @PutMapping("/")
    public TrainingDto updateTraining(@RequestBody @Valid TrainingDto training, @PathVariable Long adviceId) {
        return trainingFacade.updateTraining(training, adviceId);
    }

    @DeleteMapping("/")
    public void deleteTraining(@PathVariable Long adviceId) {
        trainingFacade.deleteTraining(adviceId);
    }
}
