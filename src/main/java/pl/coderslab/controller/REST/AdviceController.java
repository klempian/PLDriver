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
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.facade.AdviceFacade;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/advice")
@RestController
public class AdviceController {

    private AdviceFacade adviceFacade;

    @Autowired
    public AdviceController(AdviceFacade adviceFacade) {
        this.adviceFacade = adviceFacade;
    }

    @GetMapping("/")
    public List<AdviceDto> list() {
        return adviceFacade.getAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public AdviceDto create(@RequestBody @Valid AdviceDto newAdvice) {
        return adviceFacade.createAdvice(newAdvice);
    }

    @GetMapping("/{adviceId}")
    public AdviceDto getById(@PathVariable Long adviceId) {
        return adviceFacade.getById(adviceId);
    }

    @GetMapping("/tag/{tagName}")
    public List<AdviceDto> listByTag(@PathVariable String tagName) {
        return adviceFacade.getByTagName(tagName);
    }

//        @GetMapping("/tag_{tag_name}")
//        public List<AdviceDto> adviceListByTagAndContaining(@PathVariable String tag_name) { return adviceFacade.getByTagName(tag_name); }

    @PutMapping("/{adviceId}")
    public AdviceDto update(@RequestBody @Valid AdviceDto advice, @PathVariable Long adviceId) {
        return adviceFacade.updateAdvice(advice, adviceId);
    }

    @DeleteMapping("/{adviceId}")
    public void delete(@PathVariable Long adviceId) {
        adviceFacade.deleteAdvice(adviceId);
    }

    @GetMapping("/weekly")
    public AdviceDto getWeekly() {
        return adviceFacade.getWeeklyAdvice();
    }
}
