package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException;

    public boolean shouldQuit() {
        return false;
    };

    public boolean shouldSave() {
        return false;
    }
}
