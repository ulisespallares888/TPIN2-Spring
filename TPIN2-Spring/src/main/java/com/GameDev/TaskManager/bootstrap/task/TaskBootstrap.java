package com.GameDev.TaskManager.bootstrap.task;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.model.record.game.GameRecordCsv;
import com.GameDev.TaskManager.model.record.task.TaskRecordCsv;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.repository.task.TaskRepository;
import com.GameDev.TaskManager.service.csv.task.TaskServiceCsv;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Component
public class TaskBootstrap implements CommandLineRunner {
    private final TaskRepository taskRepository;
    private final TaskServiceCsv taskServiceCsv;
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running BootstrapTask");
        loadTaskData();
    }

    private Developer selectRandomDeveloper(){
        List<Developer> developerList = developerRepository.findAll();
        int randomDeveloper = (int) (Math.random() * 999) + 1;
        return developerList.get(randomDeveloper);
    }

    private Game selectRandomGame(){
        List<Game> gameList = gameRepository.findAll();
        int randomGame = (int) (Math.random() * 999) + 1;
        return gameList.get(randomGame);
    }
    private void loadTaskData() throws FileNotFoundException {
        if (taskRepository.count() < 1000){
            File file = ResourceUtils.getFile("classpath:csvdata/tasks_data.csv");
            List<TaskRecordCsv> taskRecordCsvList = taskServiceCsv.convertCSV(file);

            if (!taskRecordCsvList.isEmpty()){
                log.info("Loading database with developers");
                for (TaskRecordCsv taskRecordCsv: taskRecordCsvList) {
                    taskRepository.save(
                            Task.builder()
                                    .uuid(UUID.randomUUID())
                                    .description(taskRecordCsv.getDescription())
                                    .stateEnum(taskRecordCsv.getStateEnum())
                                    .deadLine(taskRecordCsv.getDeadLine())
                                    .responsibleDeveloper(selectRandomDeveloper())
                                    .game(selectRandomGame())
                                    .build()
                    );
                }
            }
        }
    }
}
