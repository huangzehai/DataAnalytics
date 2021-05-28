package hzh.dataanalytics.service;

public interface CounterService {
    boolean increment(String projectId, String counterName, Long initialCount, long maxCount);

    void decrement(String projectId, String counterName);
}
