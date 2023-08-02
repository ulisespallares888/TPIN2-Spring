package com.GameDev.TaskManager.service.csv.developer;

import com.GameDev.TaskManager.model.record.developer.DeveloperRecordCsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface DeveloperServiceCsv {
    List<DeveloperRecordCsv> convertCSV(File file) throws FileNotFoundException;
}
