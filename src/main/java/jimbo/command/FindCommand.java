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
    public String execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        int counter = 1;
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getData().contains(toFind)) {
                response.append(counter + ". " + task + '\n');
                counter++;
            }
        }
        if (counter == 1) {
            return "nothing found :(";
        }
        return response.toString();
    }
}
