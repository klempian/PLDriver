package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class AdviceShortDto {

    private Long id;

    private String title;

    private String content;

    private Set<@Valid TagDto> tags;

    private String multimedia;

    public Set<TagDto> getTags() {
        return new TreeSet<>(tags);
    }

}
