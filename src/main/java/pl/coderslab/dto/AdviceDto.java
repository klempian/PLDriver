package pl.coderslab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class AdviceDto {

    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(required = true, example = "Turning left")
    @NotBlank(message = "{validation.constraints.Advice.Title.NotBlank.message}")
    private String title;

    @ApiModelProperty(required = true, example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu.")
    @NotBlank(message = "{validation.constraints.Advice.Content.NotBlank.message}")
    private String content;

    @ApiModelProperty(example = "http://www.yourwebsite.com/resources/img/img1.jpg")
    private String multimedia;

    private TrainingDto training;

    private Set<@Valid TagDto> tags;

    public Set<TagDto> getTags() {
        return new TreeSet<>(tags);
    }
}
