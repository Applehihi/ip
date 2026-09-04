package jimbo;

import jimbo.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the saving and loading of data.
 */
public class Storage {
    private static final String TASK_FILE_PATH = "./tasks.txt";

    private void createFileIfNotExists() throws JimboException {
        File f = new File(TASK_FILE_PATH);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new JimboException("failed to create file");
        }
    }

    /**
     * Saves tasks to file.
     *
     * @param tasks List of tasks currently being tracked.
     * @throws JimboException If there is any error writing to file.
     */
    public void save(List<? extends Task> tasks) throws JimboException {
        StringBuilder serialisedData = new StringBuilder();
        for (Task task : tasks) {
            serialisedData.append(task.serialise());
            serialisedData.append('\n');
        }
        createFileIfNotExists();
        try {
            FileWriter fw = new FileWriter(TASK_FILE_PATH);
            fw.write(serialisedData.toString());
            fw.close();
        } catch (IOException e) {
            throw new JimboException("error while saving :(");
        }
    }

    /**
     * Loads tasks from file.
     *
     * @return The list of tasks loaded.
     * @throws JimboException If there is any error reading from file.
     */
    public List<Task> load() throws JimboException {
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
