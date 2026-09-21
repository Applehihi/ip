package jimbo.command;

import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        assert tasks != null : "tasks should not be null";

        if (tasks.isEmpty()) {
            return "no tasks stored";
        }

        return tasks.listAll();
    }
}
