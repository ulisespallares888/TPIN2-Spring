package com.GameDev.TaskManager.mapper.developer;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;

import java.util.List;

public interface DeveloperMapper{
    Developer formDtoToEntity (DeveloperDto developerDto);
    DeveloperDto formEntityToDto (Developer developer);
    List<Developer> convertListDevDtoToListEntityDev (List<DeveloperDto> developerDtos);
    List<DeveloperDto> convertListEntityDevToListDevDto (List<Developer> developers);
}
