package com.GameDev.TaskManager.model.dto.game;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Task;

import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class GameDto{
    @NotBlank
    private String title;
    private List<TaskDto> tasksDtos = new ArrayList<>();
    private List<DeveloperDto> developersDtos = new ArrayList<>();
    @NotBlank
    private String description;
    @NotNull
    private LocalDate releaseDate;
}
