package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.exception.AdviceNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.model.Tag;
import pl.coderslab.service.AdviceService;
import pl.coderslab.service.TagService;
import pl.coderslab.service.TrainingService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class AdviceFacade {

    private AdviceService adviceService;
    private TagService tagService;
    private TrainingService trainingService;
    private ModelMapper modelMapper;

    @Autowired
    public AdviceFacade(AdviceService adviceService, TagService tagService, TrainingService trainingService, ModelMapper modelMapper) {
        this.adviceService = adviceService;
        this.tagService = tagService;
        this.trainingService = trainingService;
        this.modelMapper = modelMapper;
    }

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

    public AdviceDto getWeeklyAdvice() {
        return convertToAdviceDto(adviceService.getWeeklyAdvice());
    }

    private AdviceDto convertToAdviceDto(Advice advice) {
        return modelMapper.map(advice, AdviceDto.class);
    }
    private Advice convertToAdvice(AdviceDto adviceDto) {
        Advice advice = new Advice();
        advice.setId(adviceDto.getId());
        advice.setTitle(adviceDto.getTitle());
        advice.setContent(adviceDto.getContent());
        advice.setMultimedia(adviceDto.getMultimedia());
        advice.setTraining(trainingService.findById(adviceDto.getId()).orElse(null));

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
