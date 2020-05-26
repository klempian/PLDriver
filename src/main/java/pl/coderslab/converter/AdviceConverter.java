package pl.coderslab.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.AdviceDto;
import pl.coderslab.dto.AdviceOnlyDto;
import pl.coderslab.dto.AdviceNewDto;
import pl.coderslab.dto.AdviceShortDto;
import pl.coderslab.model.Advice;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;
import pl.coderslab.service.TrainingService;

import java.util.Set;
import java.util.TreeSet;

@Component
@RequiredArgsConstructor
public class AdviceConverter {

    private final ModelMapper modelMapper;
    private final TrainingService trainingService;
    private final TagService tagService;

    public AdviceDto convertToAdviceDto(Advice advice) {
        return modelMapper.map(advice, AdviceDto.class);
    }

    public AdviceShortDto convertToAdviceShortDto(Advice advice) {
        AdviceShortDto adviceShortDto = modelMapper.map(advice, AdviceShortDto.class);
        String content = advice.getContent();
        adviceShortDto.setContent(content.substring(0, Math.min(content.length(), 100)));
        return adviceShortDto;
    }

    public AdviceOnlyDto convertToAdviceOnlyDto(Advice advice) {
        return modelMapper.map(advice, AdviceOnlyDto.class);
    }


    public Advice convertToAdvice(AdviceDto adviceDto) {
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

    public Advice convertToAdvice(AdviceNewDto adviceNewDto) {
        Advice advice = new Advice();
        advice.setTitle(adviceNewDto.getTitle());
        advice.setContent(adviceNewDto.getContent());
        advice.setMultimedia(adviceNewDto.getMultimedia());
        Set<Tag> tags = new TreeSet<>();
        adviceNewDto.getTags().forEach(
                tagDto -> {
                    Tag tag = tagService.findByName(tagDto.getName()).orElse(modelMapper.map(tagDto, Tag.class));
                    tags.add(tag);
                }
        );
        advice.setTags(tags);

        return advice;
    }
}
