package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.exception.AdviceNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.service.AdviceService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AdviceFacade {

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private ModelMapper modelMapper;

    public AdviceDto getById(Long id) {
        Advice advice = adviceService.findById(id).orElseThrow(() -> new AdviceNotFoundException(id));
        return convertToAdviceDto(advice);
    }

    public List<AdviceDto> getAll() {
        return adviceService.findAll().stream()
                .map(this::convertToAdviceDto)
                .collect(Collectors.toList());
    }

    public AdviceDto createAdvice(AdviceDto newAdvice) {
        return convertToAdviceDto(adviceService.save(convertToAdvice(newAdvice)));
    }

    public AdviceDto updateAdvice(AdviceDto adviceDto, Long advice_id) {
        Advice advice = adviceService.findById(advice_id).orElseThrow(() -> new AdviceNotFoundException(advice_id));
        adviceDto.setId(advice.getId());
        return convertToAdviceDto(adviceService.save(convertToAdvice(adviceDto)));
    }

    public void deleteAdvice(Long advice_id) {
        Advice advice = adviceService.findById(advice_id).orElseThrow(() -> new AdviceNotFoundException(advice_id));
        adviceService.delete(advice);
    }

    private AdviceDto convertToAdviceDto(Advice advice) {
        return modelMapper.map(advice, AdviceDto.class);
    }
    private Advice convertToAdvice(AdviceDto adviceDto) {return modelMapper.map(adviceDto, Advice.class);}
}
