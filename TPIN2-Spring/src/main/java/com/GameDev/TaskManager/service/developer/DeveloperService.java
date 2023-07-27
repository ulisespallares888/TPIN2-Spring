package com.GameDev.TaskManager.service.developer;

import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeveloperService  {
    List<DeveloperDto>  findAll () throws Exception;
    Optional<Developer> findById(UUID uuid)  throws Exception;
    Developer create(Developer developer)  throws Exception;
    void delete (UUID uuid)  throws Exception;
    Developer update(UUID uuid)  throws Exception;

}
