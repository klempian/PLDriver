package pl.coderslab.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity(name = "advices")
@Table(name = "advices")
public class Advice {

    public Advice() {
        tags = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String multimedia;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "advice_tag", joinColumns = @JoinColumn(name = "advice_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
//
//    @OneToOne(mappedBy = "advice", cascade = CascadeType.ALL)
//    private Training training;
}
