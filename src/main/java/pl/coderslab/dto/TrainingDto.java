package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrainingDto {

    private List<QuestionDto> questions = new ArrayList<>();
}
