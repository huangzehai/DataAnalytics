package hzh.dataanalytics.service;

import hzh.dataanalytics.entity.Table;

import java.util.List;

public interface TableService {
    Table createTable(String projectId, String tableName) throws Exception;

    List<Table> listTables(String projectId);
}
