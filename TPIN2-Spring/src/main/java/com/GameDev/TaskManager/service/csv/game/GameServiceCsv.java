package com.GameDev.TaskManager.service.csv.game;

import com.GameDev.TaskManager.model.record.game.GameRecordCsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface GameServiceCsv {
    List<GameRecordCsv> convertCSV(File file) throws FileNotFoundException;
}
