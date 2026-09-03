package jimbo;

import jimbo.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String TASK_FILE_PATH = "./tasks.txt";

    private static void createFileIfNotExists() throws IOException {
        File f = new File(TASK_FILE_PATH);
        if (!f.exists()) {
            f.createNewFile();
        }
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

    public static List<Task> load() throws JimboException {
        File f = new File(TASK_FILE_PATH);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new JimboException("preexisting save file not found");
        }

        List<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(Task.deserialise(s.nextLine()));
        }
        return tasks;
    }
}
