package pl.coderslab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity(name = "weekly_advices")
@Table(name = "weekly_advices")
public class WeeklyAdvice {

    public WeeklyAdvice() {
    }

    public WeeklyAdvice(Long advice_id) {
        this.advice_id = advice_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long advice_id;
}
