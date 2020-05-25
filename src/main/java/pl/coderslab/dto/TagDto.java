package pl.coderslab.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class TagDto implements Comparable<TagDto> {

    @Override
    public int compareTo(TagDto h) {
        return this.getName().compareTo(h.getName());
    }

    @Size(max = 30, message = "{validation.constraints.Tag.Name.Size.message}")
    @Pattern(regexp = "^[A-Za-z]+$", message = "{validation.constraints.Tag.Name.Pattern.message}")
    private String name;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
