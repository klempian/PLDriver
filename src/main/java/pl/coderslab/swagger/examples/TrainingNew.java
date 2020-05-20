package pl.coderslab.swagger.examples;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.dto.QuestionDto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrainingNew {

    @ApiModelProperty(value = "Array of questions", required = true)
    private List<QuestionNew> questions = new ArrayList<>();
}
