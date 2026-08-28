package tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskSaver {
    private static final String TASK_FILE_PATH = "./tasks.txt";

    private static void createFileIfNotExists() throws IOException {
        File f = new File(TASK_FILE_PATH);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    private static void createFile() {

    }

    public static void save(List<? extends Task> tasks) throws IOException {
        StringBuilder serialisedData = new StringBuilder();
        for (Task task : tasks) {
            serialisedData.append(task.serialise());
            serialisedData.append('\n');
        }
        createFileIfNotExists();
        FileWriter fw = new FileWriter(TASK_FILE_PATH);
        fw.write(serialisedData.toString());
        fw.close();
    }
}
