package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "tags")
@Table(name = "tags")
public class Tag implements Comparable<Tag>{

    @Override
    public int compareTo(Tag h) {
        return this.getName().compareTo(h.getName());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
