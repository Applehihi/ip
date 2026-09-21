package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;
import jimbo.task.Task;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        assert tasks != null : "tasks should not be null";

        if (index < 0 || index >= tasks.size()) {
            return "that's not a valid task :(";
        }
        Task task = tasks.get(index);
        task.unmark();
        return "unmarked the following task:\n" + task;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
