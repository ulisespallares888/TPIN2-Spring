package com.GameDev.TaskManager.model.dto.task;


import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    @NotBlank
    private String description;
    @NotNull
    private StateEnum stateEnum;
    @NotNull
    private LocalDate deadLine;
    @NotBlank
    private String gameTask;
    @NotBlank
    private String responsibleDeveloperDto;

}
