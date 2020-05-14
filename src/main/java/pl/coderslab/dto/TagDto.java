package pl.coderslab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto implements Comparable<TagDto> {

    @Override
    public int compareTo(TagDto h) {
        return this.getName().compareTo(h.getName());
    }

//    private Long id;

    private String name;

}
