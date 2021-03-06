package pl.coderslab.controller.api;

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
import pl.coderslab.converter.AdviceConverter;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.dto.AdviceOnlyDto;
import pl.coderslab.dto.AdviceNewDto;
import pl.coderslab.dto.AdviceShortDto;
import pl.coderslab.exception.ResourceNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.service.AdviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/advice")
@RestController
public class AdviceController {

    private final AdviceService adviceService;
    private final AdviceConverter adviceConverter;

    @GetMapping("/")
    public List<AdviceShortDto> list() {
        return adviceService.findAll().stream()
                .map(adviceConverter::convertToAdviceShortDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public AdviceDto create(@RequestBody @Valid AdviceNewDto newAdvice) {
        return adviceConverter.convertToAdviceDto(adviceService.save(adviceConverter.convertToAdvice(newAdvice)));
    }

    @GetMapping("/{adviceId}")
    public AdviceDto getById(@PathVariable Long adviceId) {

        Advice advice = adviceService.findById(adviceId).orElseThrow(() -> new ResourceNotFoundException("Advice", adviceId));
        return adviceConverter.convertToAdviceDto(advice);
    }

    @GetMapping("/tag/{tagName}")
    public List<AdviceShortDto> listByTag(@PathVariable String tagName) {

        return adviceService.findByTagName(tagName).stream()
                .map(adviceConverter::convertToAdviceShortDto)
                .collect(Collectors.toList());
    }

//        @GetMapping("/tag_{tag_name}")
//        public List<AdviceDto> adviceListByTagAndContaining(@PathVariable String tag_name) { return adviceFacade.getByTagName(tag_name); }

    @PutMapping("/{adviceId}")
    public AdviceDto update(@RequestBody @Valid AdviceDto adviceDto, @PathVariable Long adviceId) {
        Advice advice = adviceService.findById(adviceId).orElseThrow(() -> new ResourceNotFoundException("Advice", adviceId));
        adviceDto.setId(advice.getId());
        return adviceConverter.convertToAdviceDto(adviceService.save(adviceConverter.convertToAdvice(adviceDto)));
    }

    @DeleteMapping("/{adviceId}")
    public void delete(@PathVariable Long adviceId) {

        Advice advice = adviceService.findById(adviceId).orElseThrow(() -> new ResourceNotFoundException("Advice", adviceId));
        adviceService.delete(advice);
    }

    @GetMapping("/weekly")
    public AdviceOnlyDto getWeekly() {
        return adviceConverter.convertToAdviceOnlyDto(adviceService.getWeekly());
    }
}
