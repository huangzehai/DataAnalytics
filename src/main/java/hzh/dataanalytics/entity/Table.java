package hzh.dataanalytics.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@javax.persistence.Table(name = "t_table", uniqueConstraints = {@UniqueConstraint(name = "uk__t_table__project_id__table_name", columnNames = {"projectId", "tableName"})})
@EntityListeners(value = {AuditingEntityListener.class})
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID tableId;

    @Column(nullable = false, length = 64)
    private String tableName;

    @Column(nullable = false, length = 36)
    private String projectId;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;
}
