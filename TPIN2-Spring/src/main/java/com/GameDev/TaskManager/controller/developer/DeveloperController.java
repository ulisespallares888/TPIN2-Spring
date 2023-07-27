package com.GameDev.TaskManager.controller.developer;

import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.service.developer.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/developer")
@RequiredArgsConstructor
public class DeveloperController {
    private static final String PATH_V1 = "/api/v1/developer/";
    private final DeveloperService developerService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(developerService.findAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DeveloperDto developerDto){
        try {

            Developer developer = developerService.create(developerDto);
            String header = PATH_V1 + developer.getUuid().toString();
            return ResponseEntity.status(HttpStatus.OK).header("Location",header).body(developerDto);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(developerService.findById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" +  "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(developerService.delete(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody DeveloperDto developerDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(developerService.update(id,developerDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" + "\"}");
        }
    }
}
