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
@javax.persistence.Table(name = "t_counter", uniqueConstraints = {@UniqueConstraint(name = "uk__t_counter__project_id__counter_name", columnNames = {"projectId", "counterName"})})
@EntityListeners(value = {AuditingEntityListener.class})
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID counterId;

    @Column(nullable = false, length = 64)
    private String counterName;

    @Column(nullable = false, length = 36)
    private String projectId;

    private long count;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;
}
