package hzh.dataanalytics.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListTablesResponse {
    private int count;
    private List<TableDto> tables;
}
