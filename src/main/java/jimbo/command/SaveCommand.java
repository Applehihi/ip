package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;

public class SaveCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {

    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
