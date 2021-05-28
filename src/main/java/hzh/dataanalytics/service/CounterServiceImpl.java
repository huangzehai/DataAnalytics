package hzh.dataanalytics.service;

import hzh.dataanalytics.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
    private final CounterRepository counterRepository;

    @Autowired
    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public boolean increment(String projectId, String counterName, Long initialCount, long maxCount) {
        counterRepository.addCounterIfNotExists(projectId, counterName, initialCount == null ? 0 : initialCount);
        return counterRepository.increment(projectId, counterName, maxCount) == 1;
    }

    @Override
    public void decrement(String projectId, String counterName) {
        counterRepository.decrement(projectId, counterName);
    }
}
