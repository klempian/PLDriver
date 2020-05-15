package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import pl.coderslab.dto.TagDto;
import pl.coderslab.facade.TagFacade;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/tag")
@RestController
public class TagController {

    private TagFacade tagFacade;

    @Autowired
    public TagController(TagFacade tagFacade) {
        this.tagFacade = tagFacade;
    }

    @GetMapping("/")
    public List<TagDto> list() {
        return tagFacade.getAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createTag(@RequestBody @Valid TagDto newTag) {
        return tagFacade.createTag(newTag);
    }

    @GetMapping("/{tag_id}")
    public TagDto getById(@PathVariable Long tag_id) {
        return tagFacade.getById(tag_id);
    }

    @PutMapping("/{tag_id}")
    public TagDto updateTag(@RequestBody @Valid TagDto tag, @PathVariable Long tag_id) {
        return tagFacade.updateTag(tag, tag_id);
    }

    @DeleteMapping("/{tag_name}")
    public void deleteTag(@PathVariable String tag_name) {
        tagFacade.deleteTag(tag_name);
    }
}
