package pl.coderslab.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.TagDto;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

@Component
@RequiredArgsConstructor
public class TagConverter {

    private final ModelMapper modelMapper;
    private final TagService tagService;

    public TagDto convertToTagDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }

    public Tag convertToTag(TagDto tagDto) {
        return tagService.findByName(tagDto.getName()).orElse(modelMapper.map(tagDto, Tag.class));
    }
}
