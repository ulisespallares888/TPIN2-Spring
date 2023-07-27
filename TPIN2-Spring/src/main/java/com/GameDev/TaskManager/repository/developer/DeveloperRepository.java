package com.GameDev.TaskManager.repository.developer;

import com.GameDev.TaskManager.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
}
