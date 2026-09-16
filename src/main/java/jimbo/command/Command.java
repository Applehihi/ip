package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.ui.Ui;

/**
 * Represents a command that Jimbo can perform.
 */
public abstract class Command {
    /**
     * Runs the command.
     * @param ui Jimbo's Ui object.
     * @param tasks Jimbo's task list.
     * @param storage Jimbo's Storage object.
     * @throws JimboException If any error occurs when executing the command.
     */
    public abstract void execute(Ui ui, TaskList tasks, Storage storage) throws JimboException;

    /**
     * Returns whether Jimbo should quit after running the command.
     * @return Whether Jimbo should quit.
     */
    public boolean shouldQuit() {
        return false;
    };

    /**
     * Returns whether Jimbo should save tasks to file after running the command.
     * @return Whether Jimbo should save tasks to file.
     */
    public boolean shouldSave() {
        return false;
    }
}
