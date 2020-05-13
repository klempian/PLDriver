package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.facade.AdviceFacade;

import java.util.List;

@RequestMapping("/advice")
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
    public AdviceDto createAdvice(@RequestBody AdviceDto newAdvice) {

        return adviceFacade.createAdvice(newAdvice);
    }

    @GetMapping("/{advice_id}/")
    public AdviceDto getById(@PathVariable Long id) {
        return adviceFacade.getById(id);
    }
}
