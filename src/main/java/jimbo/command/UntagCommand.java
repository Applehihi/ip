package jimbo.command;

import jimbo.JimboException;
import jimbo.Storage;
import jimbo.TaskList;
import jimbo.task.Task;
import jimbo.ui.Ui;

public class UntagCommand extends Command {
    private int index;
    private String tag;

    public UntagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public String execute(Ui ui, TaskList tasks, Storage storage) throws JimboException {
        if (index < 0 || index >= tasks.size()) {
            return "that's not a valid task :(";
        }
        Task task = tasks.get(index);
        task.removeTag(tag);
        return "untagged task: " + task;
    }

    @Override
    public boolean shouldSave() {
        return true;
    }
}
