package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;
import jimbo.task.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        assert tasks != null : "tasks should not be null";

        if (index < 0 || index >= tasks.size()) {
            return "that's not a valid task :(";
        }
        Task task = tasks.get(index);
        task.mark();
        return "marked the following task as done:\n" + task;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
