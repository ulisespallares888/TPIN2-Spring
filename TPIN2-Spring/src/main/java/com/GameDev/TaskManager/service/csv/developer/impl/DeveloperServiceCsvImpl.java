package com.GameDev.TaskManager.service.csv.developer.impl;

import com.GameDev.TaskManager.model.record.developer.DeveloperRecordCsv;
import com.GameDev.TaskManager.service.csv.developer.DeveloperServiceCsv;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
@Slf4j
@Service
public class DeveloperServiceCsvImpl implements DeveloperServiceCsv {
    @Override
    public List<DeveloperRecordCsv> convertCSV(File file) throws FileNotFoundException {
        List<DeveloperRecordCsv> bookCsvRecordList =
                new CsvToBeanBuilder<DeveloperRecordCsv>(new FileReader(file))
                        .withType(DeveloperRecordCsv.class)
                        .build()
                        .parse();
        log.info("Turning CSV file to developers list");
        return bookCsvRecordList;
    }
}
