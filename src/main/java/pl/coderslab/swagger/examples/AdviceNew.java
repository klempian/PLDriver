package pl.coderslab.swagger.examples;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import pl.coderslab.dto.TagDto;

import javax.validation.Valid;
import java.util.Set;

@ApiModel
@Getter
public class AdviceNew {

    @ApiModelProperty(required = true, example = "Turning left")
    private String title;

    @ApiModelProperty(required = true, example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in magna ut diam facilisis suscipit at ac lectus. Praesent malesuada justo sapien, a commodo ex elementum eu.")
    private String content;

    private Set<@Valid TagDto> tags;

    @ApiModelProperty(example = "http://www.yourwebsite.com/resources/img/img1.jpg")
    private String multimedia;

}
