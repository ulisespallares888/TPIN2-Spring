package com.GameDev.TaskManager.model.dto.task;


import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String description;
    private StateEnum stateEnum;
    private LocalDate deadLine;
    private String gameTask;
    private String responsibleDeveloperDto;
   // private List<GameDto> gameDtos = new ArrayList<>();
}
