package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class AdviceNewDto {

    @NotBlank(message = "{validation.constraints.Advice.Title.NotBlank.message}")
    private String title;

    @NotBlank(message = "{validation.constraints.Advice.Content.NotBlank.message}")
    private String content;

    private Set<@Valid TagDto> tags;

    private String multimedia;

}
