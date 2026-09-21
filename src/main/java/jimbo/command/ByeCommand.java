package jimbo.command;

import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) {
        return "bye bye!";
    }

    @Override
    public boolean shouldQuit() {
        return true;
    }
}
