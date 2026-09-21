package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;
import jimbo.task.Task;

public class TaskCommand extends Command {
    Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        assert tasks != null : "tasks should not be null";

        tasks.add(task);
        return "added task: " + task;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
