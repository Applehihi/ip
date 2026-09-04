package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;
import jimbo.task.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
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
        task.mark();
        System.out.println("marked the following task as done: ");
        System.out.println(task);
        ui.printSeparator();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
