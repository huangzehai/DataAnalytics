package hzh.dataanalytics.repository;

import hzh.dataanalytics.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CounterRepository extends JpaRepository<Counter, String> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "INSERT IGNORE INTO t_counter(counter_id, project_id, counter_name, count, created_time, modified_time) VALUES(UUID(), :projectId, :counterName, :initialCount, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())", nativeQuery = true)
    int addCounterIfNotExists(String projectId, String counterName, long initialCount);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE Counter c SET c.count  = c.count + 1, modified_time  = CURRENT_TIMESTAMP() WHERE c.projectId = :projectId AND c.counterName = :counterName AND c.count < :maxCount")
    int increment(String projectId, String counterName, long maxCount);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE Counter c SET c.count  = c.count - 1, modified_time  = CURRENT_TIMESTAMP() WHERE c.projectId = :projectId AND c.counterName = :counterName")
    int decrement(String projectId, String counterName);

}
