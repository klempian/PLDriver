package pl.coderslab.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.coderslab.model.WeeklyAdvice;
import pl.coderslab.repository.AdviceRepository;
import pl.coderslab.service.WeeklyAdviceService;

import java.util.List;
import java.util.Random;

@Component
public class RandomWeeklyAdvice {

    private final AdviceRepository adviceRepository;
    private WeeklyAdviceService weeklyAdviceService;

    @Autowired
    public RandomWeeklyAdvice(AdviceRepository adviceRepository, WeeklyAdviceService weeklyAdviceService) {
        this.adviceRepository = adviceRepository;
        this.weeklyAdviceService = weeklyAdviceService;
    }

    @Scheduled(cron = "0 0 0 ? * MON")
    @EventListener(ApplicationReadyEvent.class)
    public void getRandomWeeklyAdvice() {

        List<Long> previousWeeklyAdviceIds = weeklyAdviceService.getAllAdviceIds();
        List<Long> allAdviceIds = adviceRepository.getAllIds();

        if (allAdviceIds.size() == 0) {
            return;
        }

        allAdviceIds.removeAll(previousWeeklyAdviceIds);
        if (allAdviceIds.size() == 0) {
            weeklyAdviceService.truncateTable();
            getRandomWeeklyAdvice();
            return;
        }

        Random random = new Random();
        WeeklyAdvice newWeeklyAdvice = new WeeklyAdvice(allAdviceIds.get(random.nextInt(allAdviceIds.size())));
        weeklyAdviceService.save(newWeeklyAdvice);
    }
}
