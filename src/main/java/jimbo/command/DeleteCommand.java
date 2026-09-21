package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;
import jimbo.task.Task;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        Task toDelete = tasks.get(index);
        tasks.delete(index);
        return "deleted the following task:\n" + toDelete;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
