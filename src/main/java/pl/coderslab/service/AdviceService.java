package pl.coderslab.service;

import pl.coderslab.model.Advice;

import java.util.List;
import java.util.Optional;

public interface AdviceService {

    Advice save(Advice advice);

    Optional<Advice> findById(Long id);
    List<Advice> findAll();

    List<Advice> findByTagName(String tagName);

    Advice getWeekly();

    void delete(Advice advice);
}
