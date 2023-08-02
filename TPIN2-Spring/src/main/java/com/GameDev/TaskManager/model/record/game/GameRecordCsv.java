package com.GameDev.TaskManager.model.record.game;


import com.GameDev.TaskManager.model.record.developer.DeveloperRecordCsv;
import com.GameDev.TaskManager.model.record.task.TaskRecordCsv;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameRecordCsv {
    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "release_date")
    @CsvDate("yyyy-MM-dd")
    private LocalDate releaseDate;
}
