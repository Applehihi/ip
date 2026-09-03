package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;
import jimbo.tasks.Task;

public class TaskCommand extends Command{
    Task task;

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        tasks.add(task);
        System.out.println("added task: " + task);
        ui.printSeparator();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
