package com.GameDev.TaskManager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class GameDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameDevApplication.class, args);
		log.info("## GameDevApplication is Running ##");
	}

}
