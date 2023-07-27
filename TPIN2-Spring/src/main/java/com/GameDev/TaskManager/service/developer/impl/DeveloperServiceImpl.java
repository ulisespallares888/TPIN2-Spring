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
    private final DeveloperRepository developerRepository;
    private final DeveloperMapperImpl developerMapper;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository repository,DeveloperMapperImpl developerMapper) {
        this.developerRepository = repository;
        this.developerMapper = developerMapper;
    }

    @Override
    public List<DeveloperDto> findAll() throws Exception {
        try {
            List<DeveloperDto> developerDtos = developerMapper.convertListEntityDevToListDevDto(developerRepository.findAll());
            return developerDtos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<DeveloperDto> findById(UUID uuid) throws Exception {
        try {
            Optional<DeveloperDto> optionalDeveloperDto = Optional.ofNullable(developerMapper.formEntityToDto(developerRepository.getById(uuid)));
            return optionalDeveloperDto;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public Developer create(DeveloperDto developerDto) throws Exception {
        try {
            return developerRepository.save(developerMapper.formDtoToEntity(developerDto));
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public boolean delete(UUID uuid) throws Exception {
        return false;
    }

    @Override
    public Developer update(UUID uuid, DeveloperDto developerDto) throws Exception {
        return null;
    }
}
