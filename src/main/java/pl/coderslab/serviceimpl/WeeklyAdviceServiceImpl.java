package pl.coderslab.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.WeeklyAdvice;
import pl.coderslab.repositories.WeeklyAdviceRepository;
import pl.coderslab.service.WeeklyAdviceService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WeeklyAdviceServiceImpl implements WeeklyAdviceService {

    private WeeklyAdviceRepository weeklyAdviceRepository;

    @Autowired
    public WeeklyAdviceServiceImpl(WeeklyAdviceRepository weeklyAdviceRepository) {
        this.weeklyAdviceRepository = weeklyAdviceRepository;
    }

    @Override
    public List<Long> getAllAdviceIds() {
        return weeklyAdviceRepository.getAllAdviceIds();
    }

    @Override
    public WeeklyAdvice findFirstByOrderByIdDesc() {
        return weeklyAdviceRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public void save(WeeklyAdvice weeklyAdvice) {
        weeklyAdviceRepository.save(weeklyAdvice);
    }

    @Override
    @Transactional
    public void truncateTable() {
        weeklyAdviceRepository.truncateTable();
    }
}
