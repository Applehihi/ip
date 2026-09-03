package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;
import jimbo.tasks.Task;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("that's not a valid task :(");
            ui.printSeparator();
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        System.out.println("unmarked the following task: ");
        System.out.println(task);
        ui.printSeparator();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
