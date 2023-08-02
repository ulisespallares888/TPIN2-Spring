package com.GameDev.TaskManager.service.csv.game.impl;

import com.GameDev.TaskManager.model.record.game.GameRecordCsv;
import com.GameDev.TaskManager.service.csv.game.GameServiceCsv;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
public class GameServiceCsvImpl implements GameServiceCsv {
    @Override
    public List<GameRecordCsv> convertCSV(File file) throws FileNotFoundException {
            List<GameRecordCsv> gameRecordCsvs =
                    new CsvToBeanBuilder<GameRecordCsv>(new FileReader(file))
                            .withType(GameRecordCsv.class)
                            .build()
                            .parse();
            log.info("Turning CSV file to games list");
            return gameRecordCsvs;
        }
}
