package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdviceDto {

    private Long id;

    @NotBlank(message = "{validation.constraints.Title.NotBlank.message}")
    private String title;

    @NotBlank(message = "{validation.constraints.Content.NotBlank.message}")
    private String content;

    private String multimedia;
}
