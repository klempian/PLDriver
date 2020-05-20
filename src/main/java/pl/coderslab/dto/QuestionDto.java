package pl.coderslab.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {

    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(required = true, example = "Duis fringilla justo at lacus tempus porttitor?")
    private String title;

}
