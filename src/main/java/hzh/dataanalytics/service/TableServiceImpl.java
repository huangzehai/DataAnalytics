package hzh.dataanalytics.service;

import hzh.dataanalytics.entity.Table;
import hzh.dataanalytics.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    private final CounterService counterService;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository, CounterService counterService) {
        this.tableRepository = tableRepository;
        this.counterService = counterService;
    }

    @Transactional
    @Override
    public Table createTable(String projectId, String tableName) throws Exception {
        //Check if table name exists.
        if (tableRepository.existsByProjectIdAndTableName(projectId, tableName)) {
            throw new Exception("Table name already exists.");
        }

        validateTableCount(projectId);

        Table table = new Table();
        table.setProjectId(projectId);
        table.setTableName(tableName);
        Table savedTable = tableRepository.save(table);
        TimeUnit.SECONDS.sleep(3);
        return savedTable;
    }

    private void validateTableCount(String projectId) throws Exception {
        //Check the number of tables.
        Long count = tableRepository.countByProjectId(projectId);
        boolean isSuccess = counterService.increment(projectId, "table", count, 100);
        if (!isSuccess) {
            throw new Exception("The number of tables exceeds the limit 100.");
        }
    }

    @Override
    public List<Table> listTables(String projectId) {
        return tableRepository.findByProjectId(projectId);
    }
}
