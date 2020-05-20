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
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.facade.AdviceFacade;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Advice Controller", description = "manage advices")

    @RequestMapping("/api/advice")
    @RestController
    public class AdviceRestController {

            private AdviceFacade adviceFacade;

            @Autowired
            public AdviceRestController(AdviceFacade adviceFacade) {
            this.adviceFacade = adviceFacade;
        }

        @ApiOperation(value = "Get all advices", notes = "returns all advices from database")

            @GetMapping("/")
            public List<AdviceDto> list() {
            return adviceFacade.getAll();
        }

        @ApiImplicitParam(name = "newAdvice", required = true, dataType = "AdviceNew", paramType = "body", value = "new Advice content")
        @ApiOperation(value = "Create new advice", notes = "creates new advice (without any training assigned yet)")

            @PostMapping("/")
            @ResponseStatus(HttpStatus.CREATED)
            public AdviceDto createAdvice(@RequestBody @Valid AdviceDto newAdvice) {
                return adviceFacade.createAdvice(newAdvice);
            }

        @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice")
        @ApiOperation(value = "Get advice", notes = "returns advice by it's id")

            @GetMapping("/{advice_id}")
            public AdviceDto getById(@PathVariable Long advice_id) {
            return adviceFacade.getById(advice_id);
        }

        @ApiImplicitParams({
                @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice"),
                @ApiImplicitParam(name = "advice", required = true, dataType = "AdviceDto", paramType = "body", value = "Advice content")
        })
        @ApiOperation(value = "Update advice", notes = "saves changes to advice by given advice id")

            @PutMapping("/{advice_id}")
            public AdviceDto updateAdvice(@RequestBody @Valid AdviceDto advice, @PathVariable Long advice_id) {
                return adviceFacade.updateAdvice(advice, advice_id);
            }

        @ApiImplicitParam(name = "advice_id", required = true, dataType = "int", example = "1", value = "id of the advice")
        @ApiOperation(value = "Delete advice", notes = "removes advice from database by it's id, together with it's training")

            @DeleteMapping("/{advice_id}")
            public void deleteAdvice(@PathVariable Long advice_id) {
            adviceFacade.deleteAdvice(advice_id);
        }

        @ApiOperation(value = "Get weekly advice", notes = "returns weekly random advice")

            @GetMapping("/weekly")
            public AdviceDto getWeeklyAdvice() {
            return adviceFacade.getWeeklyAdvice();
        }
    }
