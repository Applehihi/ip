package jimbo;

import jimbo.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public String listAll() {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (Task task : tasks) {
            sb.append(counter + ". " + task);
            sb.append('\n');
            counter++;
        }
        return sb.toString();
    }

    public List<Task> getInternalList() {
        return tasks;
    }
}
