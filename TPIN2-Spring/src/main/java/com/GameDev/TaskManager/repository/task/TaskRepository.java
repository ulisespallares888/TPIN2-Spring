package com.GameDev.TaskManager.repository.task;



import com.GameDev.TaskManager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
