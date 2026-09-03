package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("no tasks stored");
            ui.printSeparator();
            return;
        }

        System.out.println(tasks.listAll());
        ui.printSeparator();
    }
}
