package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;
import jimbo.tasks.Task;

public class DeleteCommand extends Command {
    int index;

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        Task toDelete = tasks.get(index);
        tasks.delete(index);
        System.out.println("deleted the following task: ");
        System.out.println(toDelete);
        ui.printSeparator();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
