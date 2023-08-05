package com.GameDev.TaskManager.service.game.impl;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;


import com.GameDev.TaskManager.mapper.game.GameMapper;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.service.developer.DeveloperService;
import com.GameDev.TaskManager.service.game.GameService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private GameMapper gameMapper;
    private DeveloperService developerService;
    private DeveloperRepository developerRepository;

    @Override
    @Transactional
    public List<GameDto> findAll() {
        List<GameDto> gameDtos = gameMapper.convertListEntityGameToListGameDto(gameRepository.findAll());
        return gameDtos;
    }

    @Override
    @Transactional
    public Optional<GameDto> findById(UUID uuid) {
        Optional<GameDto> gameDtoOptional = Optional.ofNullable(gameMapper.formEntityToDto(gameRepository.getById(uuid)));
        return gameDtoOptional;
    }

    @Override
    @Transactional
    public Game create(GameDto gameDto) throws Exception {
        try {
            return gameRepository.save(gameMapper.formDtoToEntity(gameDto));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(UUID uuid) {
        if(findById(uuid).isPresent()){
            gameRepository.deleteById(uuid);
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public Optional<GameDto> update(UUID uuid, GameDto gameDto) throws Exception {
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

    @Override
    @Transactional
    public Optional<GameDto>  addDeveloperByBody(UUID uuid, DeveloperDto developerDto) throws Exception {
        Optional<Game> gameOptional = gameRepository.findById(uuid);

        if (gameOptional.isPresent()) {
            Developer developerNew = developerService.create(developerDto);
            gameOptional.get().getDevelopers().add(developerNew);
            gameRepository.saveAndFlush(gameOptional.get());
            return Optional.of(gameMapper.formEntityToDto(gameOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<GameDto> addDeveloperById(UUID idGame, UUID idDeveloper) throws Exception{
        Optional<Game> gameOptional = gameRepository.findById(idGame);
        Optional<Developer> developerOptional = developerRepository.findById(idDeveloper);
        if (gameOptional.isPresent() && developerOptional.isPresent()) {
            gameOptional.get().getDevelopers().add(developerOptional.get());
            gameRepository.saveAndFlush(gameOptional.get());
            return Optional.of(gameMapper.formEntityToDto(gameOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public List<GameDto> findFinishedGames() {
        List<GameDto> gameDtoList = findAll();
        List<GameDto> finisehdGamesDtoList = finishedGames(gameDtoList);
        return finisehdGamesDtoList;
    }

    private List<GameDto>  finishedGames( List<GameDto> gameDtoList) {
        List<GameDto> finisehdGamesDtoList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(GameDto gameDto: gameDtoList){
            if(today.isAfter(gameDto.getReleaseDate()) && filterTaskFinished(gameDto.getTasksDtos())){
                finisehdGamesDtoList.add(gameDto);
            }
        }
        return finisehdGamesDtoList;
    }

    private Boolean filterTaskFinished (List<TaskDto> taskDtoList){
        boolean areAllTaskCompleted = true;
        for(TaskDto taskDto: taskDtoList){
            if(!taskDto.getStateEnum().toString().equals("COMPLETED")){
                areAllTaskCompleted = false;
            }
        }
        return areAllTaskCompleted;
    }



    @Override
    public List<GameDto> findInProcessGames() {
        List<GameDto> gameDtoList = findAll();
        List<GameDto> inProcessGamesDtoList = inProcessGames(gameDtoList);
        return inProcessGamesDtoList;
    }

    private List<GameDto> inProcessGames( List<GameDto> gameDtoList) {
        List<GameDto> finisehdGamesDtoList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(GameDto gameDto: gameDtoList){
            if(today.isBefore(gameDto.getReleaseDate())){
                finisehdGamesDtoList.add(gameDto);
            }
        }
        return finisehdGamesDtoList;
    }


    @Override
    @Transactional
    public List<TaskDto> findTaskByIdGame(UUID uuid) {
        Optional<GameDto> gameDto = findById(uuid);
        if(gameDto.isPresent()){
            return gameDto.get().getTasksDtos();
        }
        return new ArrayList<>();
    }





}
