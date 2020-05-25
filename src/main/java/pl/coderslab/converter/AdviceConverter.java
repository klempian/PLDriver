package pl.coderslab.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.AdviceDto;
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
}
