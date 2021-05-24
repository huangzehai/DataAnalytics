package hzh.dataanalytics.controller;

import hzh.dataanalytics.dto.ListTablesResponse;
import hzh.dataanalytics.dto.TableDto;
import hzh.dataanalytics.entity.Table;
import hzh.dataanalytics.service.TableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/tables")
    public TableDto createTable(@RequestParam(value = "projectId", defaultValue = "") String projectId, @RequestParam(value = "tableName", defaultValue = "") String tableName) throws Exception {
        Table table = this.tableService.createTable(projectId, tableName);
        return toTableDto(table);
    }

    private TableDto toTableDto(Table table) {
        TableDto tableDto = new TableDto();
        BeanUtils.copyProperties(table, tableDto);
        return tableDto;
    }

    @GetMapping("/tables")
    public ListTablesResponse listTables(@RequestParam(value = "projectId", defaultValue = "") String projectId) {
        List<Table> tables = tableService.listTables(projectId);
        List<TableDto> tableDtos = tables.stream().map(this::toTableDto).collect(Collectors.toList());
        ListTablesResponse listTablesResponse = new ListTablesResponse();
        listTablesResponse.setTables(tableDtos);
        listTablesResponse.setCount(tableDtos.size());
        return listTablesResponse;
    }
}
