package com.GameDev.TaskManager.bootstrap.task;


import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskBootstrap {
    private String description;
    private StateEnum stateEnum;
    private LocalDate deadLine;
    private String gameTask;
    private String responsibleDeveloperDto;
   // private List<GameDto> gameDtos = new ArrayList<>();
}
