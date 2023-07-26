package com.GameDev.TaskManager.domain;

import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private StateEnum stateEnum;

    @Column
    private LocalDate deadLine;

    @Column
    private String responsibleDeveloper;

    @Column
    private String gameTask;
}
