package com.GameDev.TaskManager.service.task.impl;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.mapper.task.TaskMapper;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import com.GameDev.TaskManager.repository.task.TaskRepository;
import com.GameDev.TaskManager.service.task.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public List<TaskDto> findAll() {
        List<TaskDto> taskDto = taskMapper.convertListEntityTaskToListTaskDto(taskRepository.findAll());
        return taskDto;
    }

    @Override
    public Optional<TaskDto> findById(UUID uuid) {
        Optional<TaskDto> optionalTaskDto = Optional.ofNullable(taskMapper.formEntityToDto(taskRepository.getById(uuid)));
        return optionalTaskDto;
    }

    @Override
    public Task create(TaskDto taskDto) throws Exception {
        try {
            return taskRepository.save(taskMapper.formDtoToEntity(taskDto));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID uuid) {
        if(findById(uuid).isPresent()){
            taskRepository.deleteById(uuid);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Object update(UUID uuid, TaskDto taskDto) throws Exception {
        Optional<Task> taskDtoOptional = taskRepository.findById(uuid);

        if (taskDtoOptional.isPresent()) {

            Task taskUpdated = updating(uuid, taskDtoOptional.get(), taskDto);

            return Optional.of(taskMapper.formEntityToDto(taskUpdated));
        } else {
            return Optional.empty();
        }
    }

    private Task updating(UUID uuid , Task task, TaskDto taskDto){
        task = taskMapper.formDtoToEntity(taskDto);
        task.setUuid(uuid);
        taskRepository.saveAndFlush(task);
        return task;
    }
}
