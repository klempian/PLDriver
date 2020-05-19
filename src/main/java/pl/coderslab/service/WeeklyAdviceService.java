package pl.coderslab.service;


import pl.coderslab.model.WeeklyAdvice;

import java.util.List;

public interface WeeklyAdviceService {

    List<Long> getAllAdviceIds();
    WeeklyAdvice findFirstByOrderByIdDesc();

    void save(WeeklyAdvice weeklyAdvice);

    void truncateTable();
}
