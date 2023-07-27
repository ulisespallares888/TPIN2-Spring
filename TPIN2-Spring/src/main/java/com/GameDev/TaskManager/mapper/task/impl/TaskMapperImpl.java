package com.GameDev.TaskManager.mapper.task.impl;



import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.mapper.developer.impl.DeveloperMapperImpl;
import com.GameDev.TaskManager.mapper.game.impl.GamerMapperImpl;
import com.GameDev.TaskManager.mapper.task.TaskMapper;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapperImpl implements TaskMapper {
    private final DeveloperMapperImpl developerMapper;
    //private  GamerMapperImpl gamerMapper;
    @Autowired
    public TaskMapperImpl(DeveloperMapperImpl developerMapper) {
        this.developerMapper = developerMapper;
    }

    @Override
    public Task formDtoToEntity(TaskDto taskDto) {
        Task task = Task.builder()
                .deadLine(taskDto.getDeadLine())
                .description(taskDto.getDescription())
                //.responsibleDeveloper(developerMapper.formDtoToEntity(taskDto.getResponsibleDeveloperDto()))
                .stateEnum(taskDto.getStateEnum())
                //.games(gamerMapper.convertListGameDtoToListEntityGame(taskDto.getGameDtos()))
                .build();
        return task;
    }

    @Override
    public TaskDto formEntityToDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .deadLine(task.getDeadLine())
                .description(task.getDescription())
                //.responsibleDeveloperDto(developerMapper.formEntityToDto(task.getResponsibleDeveloper()))
                .responsibleDeveloperDto(task.getResponsibleDeveloper().getUuid().toString())
                .stateEnum(task.getStateEnum())
                .gameTask(task.getGame().getUuid().toString())
                .build();
        return taskDto;
    }

    @Override
    public List<Task> convertListTaskDtoToListEntityTask(List<TaskDto> taskDtos) {
        List<Task> tasks = new ArrayList<>();
        for(TaskDto taskDto : taskDtos){
            tasks.add(formDtoToEntity(taskDto));
        }
        return tasks;
    }

    @Override
    public List<TaskDto> convertListEntityTaskToListTaskDto(List<Task> tasks) {
        List<TaskDto> taskDto = new ArrayList<>();
        for(Task task : tasks){
            taskDto.add(formEntityToDto(task));
        }
        return taskDto;
    }


}
