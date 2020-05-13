package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdviceDto {

    private Long id;

    private String title;

    private String content;

    private String multimedia;
}
