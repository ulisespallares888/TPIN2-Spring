package com.GameDev.TaskManager.model.dto.developer;


import com.GameDev.TaskManager.domain.enumeration.RoleEnum;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDto {
    private String name;
    private String lastName;
    private String email;
    private RoleEnum roleEnum;
  //  private List<TaskDto> taskDtos = new ArrayList<>();
   // private List<GameDto> gamesDtos = new ArrayList<>();
}
