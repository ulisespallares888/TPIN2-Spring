package com.GameDev.TaskManager.service.task;


import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.model.dto.task.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<TaskDto> findAll ();
    Optional<TaskDto> findById(UUID uuid);
    Task create(TaskDto taskDto)  throws Exception;
    boolean delete (UUID uuid);
    Object update(UUID uuid, TaskDto taskDto)  throws Exception;
    List<TaskDto> findByStatus(String status);
    List<TaskDto> findOverTimeTasks();
    List<TaskDto>  findTasksOfOneDeveloper(UUID uuid);
    List<TaskDto>  findTasksOfOneGame(UUID uuid);

}
