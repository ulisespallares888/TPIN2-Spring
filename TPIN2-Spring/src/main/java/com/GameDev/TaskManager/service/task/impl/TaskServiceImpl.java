package com.GameDev.TaskManager.service.task.impl;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.mapper.task.TaskMapper;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.repository.task.TaskRepository;
import com.GameDev.TaskManager.service.developer.DeveloperService;
import com.GameDev.TaskManager.service.task.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
@Component
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final DeveloperRepository developerRepository;
    private final GameRepository gameRepository;
    @Override
    public List<TaskDto> findAll() {
        List<TaskDto> taskDto = taskMapper.convertListEntityTaskToListTaskDto(taskRepository.findAll());
        return taskDto;
    }

    @Override
    public Optional<TaskDto> findById(UUID uuid) {
        Optional<TaskDto> optionalTaskDto = Optional.ofNullable(taskMapper.formEntityToDto(taskRepository.getById(uuid)));
        if(optionalTaskDto.isPresent()){
            return Optional.of(optionalTaskDto.get());
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Task create(TaskDto taskDto) throws Exception {
        try {
            Optional<Developer> developerFound = developerRepository.findById(UUID.fromString(taskDto.getResponsibleDeveloperDto()));
            Optional<Game> gameFound = gameRepository.findById(UUID.fromString(taskDto.getGameTask()));
            if (developerFound.isPresent() || gameFound.isPresent()){

                Task taskNew = taskMapper.formDtoToEntity(taskDto);
                taskNew.setResponsibleDeveloper(developerFound.get());
                taskNew.setGame(gameFound.get());

                return taskRepository.save(taskNew);
            }
           else {
               throw new Exception();
            }
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
