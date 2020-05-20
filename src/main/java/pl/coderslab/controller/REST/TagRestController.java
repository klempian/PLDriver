package pl.coderslab.controller.REST;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "Tag Controller", description = "manage tags")

    @RequestMapping("/api/tag")
    @RestController
    public class TagRestController {

            private TagFacade tagFacade;

            @Autowired
            public TagRestController(TagFacade tagFacade) {
            this.tagFacade = tagFacade;
            }

        @ApiOperation(value = "Get all tags", notes = "returns all tags from database")

            @GetMapping("/")
            public List<TagDto> list() {
            return tagFacade.getAll();
        }

        @ApiOperation(value = "Create new tag", notes = "creates new tag")

            @PostMapping("/")
            @ResponseStatus(HttpStatus.CREATED)
            public TagDto createTag(@RequestBody @Valid TagDto newTag) {
            return tagFacade.createTag(newTag);
        }

        @ApiImplicitParam(name = "tag_name", required = true, example = "safety", value = "name of the tag")
        @ApiOperation(value = "Get tag", notes = "returns tag by it's name")

            @GetMapping("/{tag_name}")
            public TagDto getByName(@PathVariable String tag_name) {
            return tagFacade.getByName(tag_name);
        }

        @ApiImplicitParam(name = "tag_name", required = true, example = "safety", value = "name of the tag")
        @ApiOperation(value = "Rename tag", notes = "saves new tag name in the database")

            @PutMapping("/{tag_name}")
            public TagDto updateTag(@RequestBody @Valid TagDto tag, @PathVariable String tag_name) {
                return tagFacade.updateTag(tag, tag_name);
        }

        @ApiImplicitParam(name = "tag_name", required = true, example = "safety", value = "name of the tag")
        @ApiOperation(value = "Delete tag", notes = "removes tag from database and any advice-tag relations")

            @DeleteMapping("/{tag_name}")
            public void deleteTag(@PathVariable String tag_name) {
            tagFacade.deleteTag(tag_name);
        }
    }
