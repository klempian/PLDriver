package pl.coderslab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.exception.TagNotFoundException;
import pl.coderslab.model.Advice;
import pl.coderslab.repositories.AdviceRepository;
import pl.coderslab.service.AdviceService;
import pl.coderslab.service.TagService;
import pl.coderslab.service.WeeklyAdviceService;
import pl.coderslab.util.RandomWeeklyAdvice;

import java.util.List;
import java.util.Optional;

@Service
public class AdviceServiceImpl implements AdviceService {

    private AdviceRepository adviceRepository;
    private WeeklyAdviceService weeklyAdviceService;
    private RandomWeeklyAdvice randomWeeklyAdvice;
    private TagService tagService;

    @Autowired
    public AdviceServiceImpl(AdviceRepository adviceRepository, WeeklyAdviceService weeklyAdviceService, RandomWeeklyAdvice randomWeeklyAdvice, TagService tagService) {
        this.adviceRepository = adviceRepository;
        this.weeklyAdviceService = weeklyAdviceService;
        this.randomWeeklyAdvice = randomWeeklyAdvice;
        this.tagService = tagService;
    }

    @Override
    public Advice save(Advice advice) {
        return adviceRepository.save(advice);
    }

    @Override
    public Optional<Advice> findById(Long id) {
        return adviceRepository.findById(id);
    }

    @Override
    public List<Advice> findAll() {
        return adviceRepository.findAll();
    }

    @Override
    public List<Advice> findByTagName(String tagName) {

        return adviceRepository.getAllByTagsContains(tagService.findByName(tagName).orElseThrow(() -> new TagNotFoundException(tagName)));
    }

    @Override
    public Advice getWeekly() {
        if (adviceRepository.count() == 0) { return new Advice(); }

        Long adviceId = weeklyAdviceService.findFirstByOrderByIdDesc().getAdviceId();
        Optional<Advice> optionalAdvice = adviceRepository.findById(adviceId);
        if (optionalAdvice.isPresent()) {
            return optionalAdvice.get();
        } else {
            randomWeeklyAdvice.getRandomWeeklyAdvice();
            return getWeekly();
        }
    }

    @Override
    public void delete(Advice advice) {
            adviceRepository.delete(advice);
    }
}
