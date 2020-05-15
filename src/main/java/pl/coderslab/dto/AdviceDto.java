package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class AdviceDto {

    private Long id;

    @NotBlank(message = "{validation.constraints.Advice.Title.NotBlank.message}")
    private String title;

    @NotBlank(message = "{validation.constraints.Advice.Content.NotBlank.message}")
    private String content;

    private String multimedia;

    private Set<@Valid TagDto> tags;

    public Set<TagDto> getTags() {
        return new TreeSet<>(tags);
    }
}
