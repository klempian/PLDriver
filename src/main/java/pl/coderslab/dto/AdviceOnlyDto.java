package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class AdviceOnlyDto {

    private Long id;

    private String title;

    private String content;

    private String multimedia;

    private Set<@Valid TagDto> tags;

    public Set<TagDto> getTags() {
        return new TreeSet<>(tags);
    }
}
