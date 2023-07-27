package com.GameDev.TaskManager.mapper.game;



import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.model.dto.game.GameDto;

import java.util.List;

public interface GameMapper {
    Game formDtoToEntity (GameDto gameDto);
    GameDto formEntityToDto (Game game);
    List<Game> convertListGameDtoToListEntityGame (List<GameDto> gameDtos);
    List<GameDto> convertListEntityGameToListGameDto (List<Game> games);
}
