package com.GameDev.TaskManager.controller.task;



import com.GameDev.TaskManager.domain.Task;
import com.GameDev.TaskManager.model.dto.task.TaskDto;
import com.GameDev.TaskManager.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private static final String PATH_V1 = "/api/v1/task/";
    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<?> findTask(@RequestParam(name = "state",required = false) String  state){
       try{
           return ResponseEntity.status(HttpStatus.OK).body(taskService.findTask(state));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/overtime")
    public ResponseEntity<?> findOverTimeTasks(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findOverTimeTasks());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Task not found" +  "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TaskDto taskDto){
        try {

            Task task = taskService.create(taskDto);
            String header = PATH_V1 + task.getUuid().toString();
            return ResponseEntity.status(HttpStatus.OK).header("Location",header).body(taskDto);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(id).get());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Task not found" +  "\"}");
        }
    }



    @GetMapping("/developer/{id}")
    public ResponseEntity<?> findTasksOfOneDeveloper(@PathVariable(name = "id") UUID uuid){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findTasksOfOneDeveloper(uuid));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" +  "\"}");
        }
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<?> findTasksOfOneGame(@PathVariable(name = "id") UUID uuid){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.findTasksOfOneGame(uuid));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Game not found" +  "\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(taskService.delete(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Task not found" + "\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody TaskDto taskDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.update(id,taskDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Task not found" + "\"}");
        }
    }
}
