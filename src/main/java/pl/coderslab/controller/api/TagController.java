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
import pl.coderslab.converter.TagConverter;
import pl.coderslab.dto.TagDto;
import pl.coderslab.exception.ResourceNotFoundException;
import pl.coderslab.model.Tag;
import pl.coderslab.service.TagService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/tag")
@RestController
public class TagController {

    private final TagConverter tagConverter;
    private final TagService tagService;

    @GetMapping("/")
    public List<TagDto> list() {
        return tagService.findAll().stream()
                .map(tagConverter::convertToTagDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto create(@RequestBody @Valid TagDto newTag) {
        return tagConverter.convertToTagDto(tagService.save(tagConverter.convertToTag(newTag)));
    }

    @GetMapping("/{tagName}")
    public TagDto getByName(@PathVariable String tagName) {
        Tag tag = tagService.findByName(tagName).orElseThrow(() -> new ResourceNotFoundException("Tag", tagName));
        return tagConverter.convertToTagDto(tag);
    }

    @PutMapping("/{tagName}")
    public TagDto update(@RequestBody @Valid TagDto tagDto, @PathVariable String tagName) {
        tagService.findByName(tagName).orElseThrow(() -> new ResourceNotFoundException("Tag", tagName));
        return tagConverter.convertToTagDto(tagService.save(tagConverter.convertToTag(tagDto)));
    }

    @DeleteMapping("/{tagName}")
    public void delete(@PathVariable String tagName) {
        Tag tag = tagService.findByName(tagName).orElseThrow(() -> new ResourceNotFoundException("Tag", tagName));
        tag.getAdviceList().forEach(advice -> advice.getTags().remove(tag));
        tagService.delete(tag);
    }
}
