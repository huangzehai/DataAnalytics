package hzh.dataanalytics.repository;

import hzh.dataanalytics.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TableRepository extends JpaRepository<Table, UUID> {
    List<Table> findByProjectId(String projectId);

    Long countByProjectId(String projectId);

    boolean existsByProjectIdAndTableName(String projectId, String tableName);
}
