package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.dto.TagDto;
import pl.coderslab.exception.TagNotFoundException;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

public class TagFacade {

    @Autowired
    private TagService tagService;

    @Autowired
    private ModelMapper modelMapper;

    public TagDto getById(Long tag_id) {
        Tag tag = tagService.findById(tag_id).orElseThrow(() -> new TagNotFoundException(tag_id));
        return convertToTagDto(tag);
    }

    public List<TagDto> getAll() {
        return tagService.findAll().stream()
                .map(this::convertToTagDto)
                .collect(Collectors.toList());
    }

    public TagDto createTag(TagDto newTag) {
        return convertToTagDto(tagService.save(convertToTag(newTag)));
    }

    public TagDto updateTag(TagDto tagDto, Long tag_id) {
        tagService.findById(tag_id).orElseThrow(() -> new TagNotFoundException(tag_id));
        return convertToTagDto(tagService.save(convertToTag(tagDto)));
    }

    public void deleteTag(Long tag_id) {
        Tag tag = tagService.findById(tag_id).orElseThrow(() -> new TagNotFoundException(tag_id));
        tagService.delete(tag);
    }

    private TagDto convertToTagDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }
    private Tag convertToTag(TagDto tagDto) {
        return tagService.findByName(tagDto.getName()).orElse(new Tag());
    }
}
