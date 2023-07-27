package com.GameDev.TaskManager.service.game;

import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.model.dto.game.GameDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {
    List<GameDto> findAll ();
    Optional<GameDto> findById(UUID uuid);
    Game create(GameDto gameDto)  throws Exception;
    boolean delete (UUID uuid);
    Object update(UUID uuid, GameDto gameDto)  throws Exception;
}
