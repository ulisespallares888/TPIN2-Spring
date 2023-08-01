package com.GameDev.TaskManager.model.record.game;


import com.GameDev.TaskManager.model.record.developer.DeveloperRecordCsv;
import com.GameDev.TaskManager.model.record.task.TaskRecordCsv;
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
    private String title;
    private List<TaskRecordCsv> tasksDtos = new ArrayList<>();
    private List<DeveloperRecordCsv> developersDtos = new ArrayList<>();
    private String description;
    private LocalDate releaseDate;
}
