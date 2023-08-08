package com.GameDev.TaskManager.service.developer.impl;

import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.mapper.developer.DeveloperMapper;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.repository.game.GameRepository;
import com.GameDev.TaskManager.service.developer.DeveloperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private  DeveloperMapper developerMapper;
    private GameRepository gameRepository;


    @Override
    public List<DeveloperDto> findAll() {

        List<DeveloperDto> developerDtos = developerMapper.convertListEntityDevToListDevDto(developerRepository.findAll());
        return developerDtos;

    }

    @Override
    public Optional<DeveloperDto> findById(UUID uuid)  {

        Optional<DeveloperDto> optionalDeveloperDto = Optional.ofNullable(developerMapper.formEntityToDto(developerRepository.getById(uuid)));
        return optionalDeveloperDto;
    }

    @Override
    @Transactional
    public Developer create(DeveloperDto developerDto) throws Exception {
        try {
            return developerRepository.save(developerMapper.formDtoToEntity(developerDto));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public boolean delete(UUID uuid) {
        if(findById(uuid).isPresent()){
            developerRepository.deleteById(uuid);
            return true;
        }else {
            return false;
        }

    }

    @Override
    @Transactional
    public Optional<DeveloperDto> update(UUID uuid, DeveloperDto developerDto) throws Exception {
        Optional<Developer> developerOptional  = developerRepository.findById(uuid);

        if(developerOptional.isPresent()){

            Developer developerUpdated = updating(uuid,developerOptional.get(),developerDto);

            return Optional.of(developerMapper.formEntityToDto( developerUpdated));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public List<DeveloperDto> findDeveloperByIdGame(UUID id) {
        List<DeveloperDto> developerDtos = developerMapper.convertListEntityDevToListDevDto(gameRepository.findById(id).get().getDevelopers());
        if(!developerDtos.isEmpty()){
            return developerDtos;
        }
        return new ArrayList<>();
    }

    private Developer updating(UUID uuid , Developer developer, DeveloperDto developerDto){
        developer = developerMapper.formDtoToEntity(developerDto);
        developer.setUuid(uuid);
        developerRepository.saveAndFlush(developer);
        return developer;
    }


}
