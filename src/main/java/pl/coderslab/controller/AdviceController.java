package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.facade.AdviceFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdviceController {

    private AdviceFacade adviceFacade;

    @Autowired
    public AdviceController(AdviceFacade adviceFacade) {
        this.adviceFacade = adviceFacade;
    }

    @GetMapping(value = {"/advice", "/admin/advice"})
    public List<AdviceDto> list() {
        return adviceFacade.getAll();
    }

    @PostMapping("/admin/advice")
    @ResponseStatus(HttpStatus.CREATED)
    public AdviceDto createAdvice(@RequestBody @Valid AdviceDto newAdvice) {
        return adviceFacade.createAdvice(newAdvice);
    }

    @GetMapping(value = {"/advice/{advice_id}", "/admin/advice/{advice_id}"})
    public AdviceDto getById(@PathVariable Long advice_id) {
        return adviceFacade.getById(advice_id);
    }

    @PutMapping("/admin/advice/{advice_id}")
    public AdviceDto updateAdvice(@RequestBody @Valid AdviceDto advice, @PathVariable Long advice_id) {
        return adviceFacade.updateAdvice(advice, advice_id);
    }

    @DeleteMapping("/admin/advice/{advice_id}")
    public void deleteAdvice(@PathVariable Long advice_id) {
        adviceFacade.deleteAdvice(advice_id);
    }
}
