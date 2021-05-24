package hzh.dataanalytics.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TableDto {
    private UUID tableId;
    private String tableName;
    private String projectId;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
}
