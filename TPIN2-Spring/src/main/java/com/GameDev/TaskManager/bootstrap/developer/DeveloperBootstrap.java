package com.GameDev.TaskManager.bootstrap.developer;


import com.GameDev.TaskManager.domain.Developer;
import com.GameDev.TaskManager.model.record.developer.DeveloperRecordCsv;
import com.GameDev.TaskManager.repository.developer.DeveloperRepository;
import com.GameDev.TaskManager.service.csv.developer.DeveloperServiceCsv;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Component
public class DeveloperBootstrap implements CommandLineRunner {

    private final DeveloperRepository developerRepository;

    private final DeveloperServiceCsv developerServiceCsv;

    @Override
    public void run(String... args) throws Exception {
        log.info("Running BootstrapDeveloper");

        loadDeveloperData();
    }

    private void loadDeveloperData() throws FileNotFoundException {
        if (developerRepository.count() < 1000){
            File file = ResourceUtils.getFile("classpath:csvdata/developers_data.csv");
            List<DeveloperRecordCsv> developerRecordCsvs = developerServiceCsv.convertCSV(file);

            if (!developerRecordCsvs.isEmpty()){
                log.info("Loading database with developers");
                for (DeveloperRecordCsv developerRecordCsv: developerRecordCsvs) {
                    developerRepository.save(
                            Developer.builder()
                                    .uuid(UUID.randomUUID())
                                    .name(developerRecordCsv.getName())
                                    .lastName(developerRecordCsv.getLastName())
                                    .email(developerRecordCsv.getEmail())
                                    .roleEnum(developerRecordCsv.getRoleEnum())
                                    .build()
                    );
                }
            }
        }
    }
}
