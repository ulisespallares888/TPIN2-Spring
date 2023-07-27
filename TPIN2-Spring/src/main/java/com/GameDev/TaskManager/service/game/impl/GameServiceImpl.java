package com.GameDev.TaskManager.service.game.impl;


import com.GameDev.TaskManager.domain.Game;

import com.GameDev.TaskManager.mapper.game.GameMapper;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.service.game.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private  GameMapper gameMapper;

    @Override
    public List<GameDto> findAll() {
        List<GameDto> gameDtos = gameMapper.convertListEntityGameToListGameDto(gameRepository.findAll());
        return gameDtos;
    }

    @Override
    public Optional<GameDto> findById(UUID uuid) {
        Optional<GameDto> gameDtoOptional = Optional.ofNullable(gameMapper.formEntityToDto(gameRepository.getById(uuid)));
        return gameDtoOptional;
    }

    @Override
    public Game create(GameDto gameDto) throws Exception {
        try {
            return gameRepository.save(gameMapper.formDtoToEntity(gameDto));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID uuid) {
        if(findById(uuid).isPresent()){
            gameRepository.deleteById(uuid);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Object update(UUID uuid, GameDto gameDto) throws Exception {
        Optional<Game> gameOptional = gameRepository.findById(uuid);

        if (gameOptional.isPresent()) {

            Game gameUpdated = updating(uuid, gameOptional.get(), gameDto);

            return Optional.of(gameMapper.formEntityToDto(gameUpdated));
        } else {
            return Optional.empty();
        }
    }

    private Game updating(UUID uuid , Game game, GameDto gameDto){
        game = gameMapper.formDtoToEntity(gameDto);
        game.setUuid(uuid);
        gameRepository.saveAndFlush(game);
        return game;
    }


}
