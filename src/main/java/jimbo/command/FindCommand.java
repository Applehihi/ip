package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;
import jimbo.task.Task;

public class FindCommand extends Command {
    String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getData().contains(toFind)) {
                System.out.println(counter + ". " + task);
                counter++;
            }
        }
        if (counter == 1) {
            System.out.println("nothing found :(");
        }
        ui.printSeparator();
    }
}
