package com.GameDev.TaskManager.service.game;

import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {
    List<GameDto> findAll ();
    Optional<GameDto> findById(UUID uuid);
    Game create(GameDto gameDto)  throws Exception;
    boolean delete (UUID uuid);
    Optional<GameDto> update(UUID uuid, GameDto gameDto)  throws Exception;
    Optional<GameDto>  addDeveloperByBody(UUID uuid, DeveloperDto developerDto) throws Exception ;
    Optional<GameDto> addDeveloperById(UUID idGame, UUID idDeveloper)throws Exception;
    List<GameDto> findFinishedGames();
    List<GameDto> findInProcessGames();
    List<DeveloperDto> findDeveloperByIdGame(UUID uuid);
    List<TaskDto> findTaskByIdGame(UUID uuid);
}
