package com.GameDev.TaskManager.service.developer;

import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeveloperService  {
    List<DeveloperDto>  findAll ();
    Optional<DeveloperDto> findById(UUID uuid);
    Developer create(DeveloperDto developerDto)  throws Exception;
    boolean delete (UUID uuid);
    Object update(UUID uuid, DeveloperDto developerDto)  throws Exception;

}
