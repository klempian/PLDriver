package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.model.Advice;
import pl.coderslab.service.AdviceService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdviceFacade {

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private ModelMapper modelMapper;

    public AdviceDto getById(Long id) {
        return convertToAdviceDto(adviceService.findById(id));
    }

    public List<AdviceDto> getAll() {
        return adviceService.findAll().stream()
                .map(this::convertToAdviceDto)
                .collect(Collectors.toList());
    }

    private AdviceDto convertToAdviceDto(Advice advice) {
        return modelMapper.map(advice, AdviceDto.class);
    }
}
