package com.GameDev.TaskManager.model.dto.game;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Task;

import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
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
public class GameDto{
    private String title;
    private List<TaskDto> tasksDtos = new ArrayList<>();
    private List<DeveloperDto> developersDtos = new ArrayList<>();
    private String description;
    private LocalDate releaseDate;
}
