package com.GameDev.TaskManager.bootstrap.game;

import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.model.record.game.GameRecordCsv;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.service.csv.game.GameServiceCsv;
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
public class GameBootstrap implements CommandLineRunner {

    private final GameRepository gameRepository;
    private final GameServiceCsv gameServiceCsv;
    private final DeveloperRepository developerRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running BootstrapGame");
        loadGameData();
    }

    private List<Developer> selectRandomDevelopers(){
        List<Developer> developerList = developerRepository.findAll();
        int randomCountDevelopers = (int) (Math.random() * 20) + 1;
        List<Developer> randomdevelopersList = new ArrayList<>();

        for (int i=1; i<randomCountDevelopers;i++){
            int randomPosicionDeveloper = (int) (Math.random() * 999) + 1;
            randomdevelopersList.add(developerList.get(randomPosicionDeveloper));
        }
        return randomdevelopersList;
    }

    private void loadGameData() throws FileNotFoundException {
        if (gameRepository.count() < 1000){
            File file = ResourceUtils.getFile("classpath:csvdata/games_data.csv");
            List<GameRecordCsv> gameRecordCsvList = gameServiceCsv.convertCSV(file);

            if (!gameRecordCsvList.isEmpty()){
                log.info("Loading database with developers");
                for (GameRecordCsv gameRecordCsv: gameRecordCsvList) {
                    gameRepository.save(
                            Game.builder()
                                    .uuid(UUID.randomUUID())
                                    .title(gameRecordCsv.getTitle())
                                    .releaseDate(gameRecordCsv.getReleaseDate())
                                    .description(gameRecordCsv.getDescription())
                                    .developers(selectRandomDevelopers())
                                    .build()
                    );
                }
            }
        }
    }
}
