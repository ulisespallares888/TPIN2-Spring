package com.GameDev.TaskManager.mapper.developer.impl;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.mapper.developer.DeveloperMapper;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class DeveloperMapperImpl implements DeveloperMapper {


    @Override
    public Developer formDtoToEntity(DeveloperDto developerDto) {
        Developer developer = Developer.builder()
                .email(developerDto.getEmail())
                .name(developerDto.getName())
                .lastName(developerDto.getLastName())
                .roleEnum(developerDto.getRoleEnum())
                //.games(gamerMapper.convertListGameDtoToListEntityGame(developerDto.getGamesDtos()))
                //.tasks(taskMapper.convertListTaskDtoToListEntityTask(developerDto.getTaskDtos()))
                .build();
        return developer;
    }
    @Override
    public List<Developer> convertListDevDtoToListEntityDev (List<DeveloperDto> developerDtos){
        List<Developer> developers = new ArrayList<>();
        for(DeveloperDto developerDto : developerDtos){
            developers.add(formDtoToEntity(developerDto));
        }
        return developers;
    }

    @Override
    public DeveloperDto formEntityToDto(Developer developer) {
        DeveloperDto developerDto = DeveloperDto.builder()
                .name(developer.getName())
                .lastName(developer.getLastName())
                .email(developer.getEmail())
                .roleEnum(developer.getRoleEnum())
                //.gamesDtos(gamerMapper.convertListEntityGameToListGameDto(developer.getGames()))
               // .taskDtos(taskMapper.convertListEntityTaskToListTaskDto(developer.getTasks()))
                .build();
        return developerDto;
    }
    @Override
    public List<DeveloperDto> convertListEntityDevToListDevDto (List<Developer> developers){
        List<DeveloperDto> developerDtos = new ArrayList<>();
        for(Developer developer : developers){
            developerDtos.add(formEntityToDto(developer));
        }
        return developerDtos;
    }
}
