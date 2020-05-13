package pl.coderslab.service;

import pl.coderslab.model.Advice;

import java.util.List;

public interface AdviceService {

    void save(Advice advice);

    Advice findById(Long id);
    List<Advice> findAll();

    void deleteById(Long id);
}
