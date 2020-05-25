package pl.coderslab.controller.REST;

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

@RequestMapping("/api/tag")
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

    @GetMapping("/{tagName}")
    public TagDto getByName(@PathVariable String tagName) {
        return tagFacade.getByName(tagName);
    }

    @PutMapping("/{tagName}")
    public TagDto updateTag(@RequestBody @Valid TagDto tag, @PathVariable String tagName) {
        return tagFacade.updateTag(tag, tagName);
    }

    @DeleteMapping("/{tagName}")
    public void deleteTag(@PathVariable String tagName) {
        tagFacade.deleteTag(tagName);
    }
}
