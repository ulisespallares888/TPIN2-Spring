package com.GameDev.TaskManager.model.record.task;


import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskRecordCsv {
    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "state_enum")
    private StateEnum stateEnum;

    @CsvBindByName(column = "dead_line")
    @CsvDate("yyyy-MM-dd")
    private LocalDate deadLine;

    @CsvBindByName(column = "game_uuid")
    private String gameTask;

    @CsvBindByName(column = "developer_uuid")
    private String responsibleDeveloper;

}
