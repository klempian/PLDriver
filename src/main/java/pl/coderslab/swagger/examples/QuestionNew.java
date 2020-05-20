package pl.coderslab.swagger.examples;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class QuestionNew {

    @ApiModelProperty(required = true, example = "Duis fringilla justo at lacus tempus porttitor?")
    private String title;

}
