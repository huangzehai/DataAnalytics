package hzh.dataanalytics.service;

import hzh.dataanalytics.entity.Table;
import hzh.dataanalytics.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.LockModeType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Transactional
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Override
    public Table createTable(String projectId, String tableName) throws Exception {
        //Check the number of tables.
        Long count = tableRepository.countByProjectId(projectId);
        if (count >= 100) {
            throw new Exception("The number of tables exceeds the limit 100.");
        }

        //Check if table name exists.
        if (tableRepository.existsByProjectIdAndTableName(projectId, tableName)) {
            throw new Exception("Table name already exists.");
        }

        Table table = new Table();
        table.setProjectId(projectId);
        table.setTableName(tableName);
        Table savedTable = tableRepository.save(table);
        TimeUnit.SECONDS.sleep(4);
        return savedTable;
    }

    @Override
    public List<Table> listTables(String projectId) {
        return tableRepository.findByProjectId(projectId);
    }
}
