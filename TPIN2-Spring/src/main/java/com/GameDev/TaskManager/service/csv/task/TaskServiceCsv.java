package com.GameDev.TaskManager.service.csv.task;

import com.GameDev.TaskManager.model.record.task.TaskRecordCsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface TaskServiceCsv {
    List<TaskRecordCsv> convertCSV(File file) throws FileNotFoundException;

}
