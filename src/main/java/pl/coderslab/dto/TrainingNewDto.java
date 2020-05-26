package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TrainingNewDto {

    private List<QuestionNewDto> questions = new ArrayList<>();
}
