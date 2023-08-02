package com.GameDev.TaskManager.service.csv.task.impl;

import com.GameDev.TaskManager.model.record.task.TaskRecordCsv;
import com.GameDev.TaskManager.service.csv.task.TaskServiceCsv;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
public class TaskServiceCsvImpl implements TaskServiceCsv {
    @Override
    public List<TaskRecordCsv> convertCSV(File file) throws FileNotFoundException {
        List<TaskRecordCsv> taskRecordCsvList =
                new CsvToBeanBuilder<TaskRecordCsv>(new FileReader(file))
                        .withType(TaskRecordCsv.class)
                        .build()
                        .parse();
        log.info("Turning CSV file to tasks list");
        return taskRecordCsvList;
    }
}
