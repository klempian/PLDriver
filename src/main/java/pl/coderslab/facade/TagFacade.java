package pl.coderslab.facade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.TagDto;
import pl.coderslab.exception.TagNotFoundException;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagFacade {

    private TagService tagService;
    private ModelMapper modelMapper;

    @Autowired
    public TagFacade(TagService tagService, ModelMapper modelMapper) {
        this.tagService = tagService;
        this.modelMapper = modelMapper;
    }

    public TagDto getByName(String tag_name) {
        Tag tag = tagService.findByName(tag_name).orElseThrow(() -> new TagNotFoundException(tag_name));
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

    public TagDto updateTag(TagDto tagDto, String tag_name) {
        tagService.findByName(tag_name).orElseThrow(() -> new TagNotFoundException(tag_name));
        return convertToTagDto(tagService.save(convertToTag(tagDto)));
    }

    public void deleteTag(String tag_name) {
        Tag tag = tagService.findByName(tag_name).orElseThrow(() -> new TagNotFoundException(tag_name));
        tag.getAdviceList().forEach(advice -> advice.getTags().remove(tag));
        tagService.delete(tag);
    }

    private TagDto convertToTagDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }
    private Tag convertToTag(TagDto tagDto) {
        return tagService.findByName(tagDto.getName()).orElse(modelMapper.map(tagDto, Tag.class));
    }
}
