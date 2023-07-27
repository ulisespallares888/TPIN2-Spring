package com.GameDev.TaskManager.model.dto.task;


import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private DeveloperDto responsibleDeveloperDto;
   // private List<GameDto> gameDtos = new ArrayList<>();
}
