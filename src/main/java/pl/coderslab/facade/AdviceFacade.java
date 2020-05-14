package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.exception.AdviceNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.model.Tag;
import pl.coderslab.service.AdviceService;
import pl.coderslab.service.TagService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AdviceFacade {

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private TagService tagService;

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
        newAdvice.setId(null);
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
//    private Advice convertToAdvice(AdviceDto adviceDto) {return modelMapper.map(adviceDto, Advice.class);}
    private Advice convertToAdvice(AdviceDto adviceDto) {
        Advice advice = new Advice();
        advice.setId(adviceDto.getId());
        advice.setTitle(adviceDto.getTitle());
        advice.setContent(adviceDto.getContent());
        advice.setMultimedia(adviceDto.getMultimedia());

        Set<Tag> tags = new TreeSet<>();
        adviceDto.getTags().forEach(
                tagDto -> {
                    Tag tag = tagService.findByName(tagDto.getName()).orElse(modelMapper.map(tagDto, Tag.class));
                    tags.add(tag);
                }
        );
        advice.setTags(tags);

        return advice;
    }
}
