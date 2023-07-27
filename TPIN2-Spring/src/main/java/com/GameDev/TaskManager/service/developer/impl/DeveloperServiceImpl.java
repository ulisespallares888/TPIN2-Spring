package com.GameDev.TaskManager.service.developer.impl;

import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.mapper.developer.impl.DeveloperMapperImpl;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.service.developer.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository repository;
    private final DeveloperMapperImpl developerMapper;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository repository,DeveloperMapperImpl developerMapper) {
        this.repository = repository;
        this.developerMapper = developerMapper;
    }

    @Override
    public List<DeveloperDto> findAll() throws Exception {
        try {
            List<DeveloperDto> developerDtos = developerMapper.convertListEntityDevToListDevDto(repository.findAll());
            return developerDtos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Developer> findById(UUID uuid) throws Exception {
        return Optional.empty();
    }

    @Override
    public Developer create(Developer developer) throws Exception {
        return null;
    }

    @Override
    public void delete(UUID uuid) throws Exception {

    }

    @Override
    public Developer update(UUID uuid) throws Exception {
        return null;
    }
}
