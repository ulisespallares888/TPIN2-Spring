package com.GameDev.TaskManager.service.task.impl;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.domain.enumeration.StateEnum;
import com.GameDev.TaskManager.mapper.task.TaskMapper;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.repository.task.TaskRepository;
import com.GameDev.TaskManager.service.task.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<TaskDto> findTask(String state) {
        if (state != null) {
            return findByStatus(state);
        }
        return findAll();
    }


    @Override
    public Optional<TaskDto> updateState(UUID uuid, StateEnum stateEnum) {
        Optional<Task> optionalTask = Optional.of(taskRepository.getById(uuid));
        optionalTask.get().setStateEnum(stateEnum);
        taskRepository.saveAndFlush(optionalTask.get());
        return Optional.ofNullable(taskMapper.formEntityToDto(optionalTask.get()));
    }

    @Override
    public List<TaskDto> findAll() {
        List<TaskDto> taskDtoList = taskMapper.convertListEntityTaskToListTaskDto(taskRepository.findAll());
        return taskDtoList;
    }

    @Override
    public List<TaskDto> findByStatus(String state) {
        List<TaskDto> taskDtoList = taskMapper.convertListEntityTaskToListTaskDto(taskRepository.findAll());
        List<TaskDto> taskDtoListOutput = new ArrayList<>();
        for(TaskDto taskDto: taskDtoList){
            if(taskDto.getStateEnum().toString().equalsIgnoreCase(state)){
                taskDtoListOutput.add(taskDto);
            }
        }
        return taskDtoListOutput;
    }

    @Override
    public List<TaskDto> findOverTimeTasks() {
        List<TaskDto> taskDtoList = taskMapper.convertListEntityTaskToListTaskDto(taskRepository.findAll());
        List<TaskDto> taskDtoListOverTime = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (TaskDto taskDto: taskDtoList){
            if(taskDto.getDeadLine().isBefore(today)){
                if(!taskDto.getStateEnum().equals("COMPLETED")){
                    taskDtoListOverTime.add(taskDto);
                }
            }
        }
        return taskDtoListOverTime;
    }

    @Override
    public List<TaskDto> findTasksOfOneDeveloper(UUID uuid) {
        Optional<Developer> developerOptional = developerRepository.findById(uuid);

        if (developerOptional.isPresent()){
            List<TaskDto> taskDtoList = taskMapper.convertListEntityTaskToListTaskDto(developerOptional.get().getTasks());
            return taskDtoList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<TaskDto> findTasksOfOneGame(UUID uuid) {
        Optional<Game> gameOptional = gameRepository.findById(uuid);
        if(gameOptional.isPresent()){
            List<TaskDto> taskDtoList = taskMapper.convertListEntityTaskToListTaskDto(gameOptional.get().getTasks());
            return taskDtoList;
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<TaskDto> findById(UUID uuid) {
        Optional<Task> optionalTask = Optional.of(taskRepository.getById(uuid));
        return optionalTask.map(taskMapper::formEntityToDto);

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
        Optional<Developer> developerOptional = developerRepository.findById(taskDtoOptional.get().getResponsibleDeveloper().getUuid());
        Optional<Game> gameOptional = gameRepository.findById(taskDtoOptional.get().getGame().getUuid());

        if (taskDtoOptional.isPresent() && developerOptional.isPresent() && gameOptional.isPresent()) {

            Task taskUpdated = updating(uuid, taskDtoOptional, taskDto, developerOptional,gameOptional);

            return Optional.of(taskMapper.formEntityToDto(taskUpdated));
        } else {
            return Optional.empty();
        }
    }

    private Task updating(UUID uuid , Optional<Task> task, TaskDto taskDto, Optional<Developer> developerOptional, Optional<Game> gameOptional){

        task = Optional.ofNullable(taskMapper.formDtoToEntity(taskDto));
        task.get().setUuid(uuid);
        task.get().setResponsibleDeveloper(developerOptional.get());
        task.get().setGame(gameOptional.get());
        taskRepository.saveAndFlush(task.get());

        return task.get();
    }
}
