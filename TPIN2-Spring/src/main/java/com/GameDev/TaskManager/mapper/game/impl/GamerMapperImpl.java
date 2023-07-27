package com.GameDev.TaskManager.mapper.game.impl;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.mapper.developer.impl.DeveloperMapperImpl;
import com.GameDev.TaskManager.mapper.game.GameMapper;
import com.GameDev.TaskManager.mapper.task.impl.TaskMapperImpl;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GamerMapperImpl implements GameMapper {

    private DeveloperMapperImpl developerMapper;
    private TaskMapperImpl taskMapper;
    @Override
    public Game formDtoToEntity(GameDto gameDto) {
        Game game = Game.builder()
                .description(gameDto.getDescription())
                .title(gameDto.getTitle())
                .releaseDate(gameDto.getReleaseDate())
                .developers(developerMapper.convertListDevDtoToListEntityDev(gameDto.getDevelopersDtos()))
                .tasks(taskMapper.convertListTaskDtoToListEntityTask(gameDto.getTasksDtos()))                 .build();
        return game;
    }

    @Override
    public GameDto formEntityToDto(Game game) {
        GameDto gameDto = GameDto.builder()
                .description(game.getDescription())
                .title(game.getTitle())
                .releaseDate(game.getReleaseDate())
                .developersDtos(developerMapper.convertListEntityDevToListDevDto(game.getDevelopers()))
                .tasksDtos(taskMapper.convertListEntityTaskToListTaskDto(game.getTasks()))
                .build();
        return gameDto;
    }

    @Override
    public List<Game> convertListGameDtoToListEntityGame(List<GameDto> gameDtos) {
        List<Game> games = new ArrayList<>();
        for(GameDto gameDto : gameDtos){
            games.add(formDtoToEntity(gameDto));
        }
        return games;
    }

    @Override
    public List<GameDto> convertListEntityGameToListGameDto(List<Game> games) {
        List<GameDto> gamesDto = new ArrayList<>();
        for(Game game : games){
            gamesDto.add(formEntityToDto(game));
        }
        return gamesDto;
    }


}
