package com.GameDev.TaskManager.controller.game;



import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.domain.Game;
import com.GameDev.TaskManager.model.dto.developer.DeveloperDto;
import com.GameDev.TaskManager.model.dto.game.GameDto;
import com.GameDev.TaskManager.service.game.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("api/v1/game")
@RequiredArgsConstructor
public class GameController{
    private static final String PATH_V1 = "/api/v1/game/";
    private final GameService gameService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody GameDto gameDto){
        try {

            Game game = gameService.create(gameDto);
            String header = PATH_V1 + game.getUuid().toString();
            return ResponseEntity.status(HttpStatus.OK).header("Location",header).body(gameDto);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gameService.findById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" +  "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gameService.delete(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" + "\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody GameDto gameDto) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(gameService.update(id,gameDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" +"Developer not found" + "\"}");
        }
    }
}
