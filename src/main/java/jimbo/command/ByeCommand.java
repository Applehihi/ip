package jimbo.command;

import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {

    }

    @Override
    public boolean shouldQuit() {
        return true;
    }
}
