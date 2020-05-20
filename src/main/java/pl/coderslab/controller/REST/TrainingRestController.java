package pl.coderslab.controller.REST;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "Training Controller", description = "manage trainings")
@RequestMapping("/api/advice/{advice_id}/training")
@RestController
public class TrainingRestController {

    private TrainingFacade trainingFacade;

    @Autowired
    public TrainingRestController(TrainingFacade trainingFacade) {
        this.trainingFacade = trainingFacade;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice"),
            @ApiImplicitParam(name = "newTraining", required = true, dataType = "TrainingNew", paramType = "body", value = "New training content")
    })
    @ApiOperation(value = "Create new training", notes = "creates new training")

        @PostMapping("/")
        @ResponseStatus(HttpStatus.CREATED)
        public TrainingDto createTraining(@PathVariable Long advice_id, @RequestBody @Valid TrainingDto newTraining) {
            return trainingFacade.createTraining(newTraining, advice_id);
        }

    @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice")
    @ApiOperation(value = "Get training", notes = "returns training by advice id")

        @GetMapping("/")
        public TrainingDto getByAdviceId(@PathVariable Long advice_id) {
            return trainingFacade.getById(advice_id);
        }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice"),
            @ApiImplicitParam(name = "training", required = true, dataType = "TrainingDto", paramType = "body", value = "Training content")
    })
    @ApiOperation(value = "Update training", notes = "saves changes to training by given advice id")

        @PutMapping("/")
        public TrainingDto updateTraining(@RequestBody @Valid TrainingDto training, @PathVariable Long advice_id) {
            return trainingFacade.updateTraining(training, advice_id);
        }

    @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice")
    @ApiOperation(value = "Delete training", notes = "removes training from database together with questions")

        @DeleteMapping("/")
        public void deleteTraining(@PathVariable Long advice_id) {
            trainingFacade.deleteTraining(advice_id);
        }
}
