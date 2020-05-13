package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.facade.AdviceFacade;

import java.util.List;

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
}
