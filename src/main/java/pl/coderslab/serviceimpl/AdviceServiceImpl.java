package pl.coderslab.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Advice;
import pl.coderslab.repository.AdviceRepository;
import pl.coderslab.service.AdviceService;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {

    private AdviceRepository adviceRepository;

    @Autowired
    public AdviceServiceImpl(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @Override
    public void save(Advice advice) {
        adviceRepository.save(advice);
    }

    @Override
    public Advice findById(Long id) {
        return adviceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Advice> findAll() {
        return adviceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (findById(id) != null) {
            adviceRepository.deleteById(id);
        }
    }
}
