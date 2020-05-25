package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "weekly_advices")
public class WeeklyAdvice {

    public WeeklyAdvice() {
    }

    public WeeklyAdvice(Long adviceId) {
        this.adviceId = adviceId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adviceId;
}
