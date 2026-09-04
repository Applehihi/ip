package jimbo;

import jimbo.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from a raw Java List of tasks.
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on the index provided.
     * @param index The index of the task to delete.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Gets the task in the list with the specified index.
     * @param index The index of the task.
     * @return The task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Calculates how many total tasks are in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the list is empty
     * @return Whether the list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks formatted as a String.
     * @return The list tasks as a String.
     */
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

    /**
     * Returns the raw internal List representation of the tasks.
     * @return Raw List of tasks.
     */
    public List<Task> getInternalList() {
        return tasks;
    }
}
