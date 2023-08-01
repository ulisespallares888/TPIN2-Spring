package com.GameDev.TaskManager.bootstrap.game;


import com.GameDev.TaskManager.bootstrap.developer.DeveloperBootstrap;
import com.GameDev.TaskManager.bootstrap.task.TaskBootstrap;
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
public class GameBootstrap {
    private String title;
    private List<TaskBootstrap> tasksDtos = new ArrayList<>();
    private List<DeveloperBootstrap> developersDtos = new ArrayList<>();
    private String description;
    private LocalDate releaseDate;
}
