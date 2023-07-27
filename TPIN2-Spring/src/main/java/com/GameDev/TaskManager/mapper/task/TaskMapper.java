package com.GameDev.TaskManager.mapper.task;



import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.model.dto.task.TaskDto;

import java.util.List;

public interface TaskMapper {
    Task formDtoToEntity (TaskDto taskDto);
    TaskDto formEntityToDto (Task task);
    List<Task> convertListTaskDtoToListEntityTask (List<TaskDto> taskDtos);
    List<TaskDto> convertListEntityTaskToListTaskDto (List<Task> tasks);
}
