package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {

    }

    @Override
    public boolean shouldQuit() {
        return true;
    }
}
