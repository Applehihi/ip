package jimbo;

import javafx.application.Platform;
import jimbo.command.Command;
import jimbo.ui.Ui;

/**
 * The main app class.
 *
 * Jimbo can add and delete tasks, mark and unmark them as deleted,
 * and save and load them from a while.
 */
public class Jimbo {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initialises Jimbo.
     */
    public Jimbo() {
        ui = new Ui();
        storage = new Storage();

        ui.greet();

        System.out.println("trying to load saved tasks...");
        try {
            tasks = new TaskList(storage.load());
            System.out.println("tasks loaded! " + tasks.size() + " tasks found");
        } catch (JimboException e) {
            System.out.println(e.getMessage());
            ui.printSeparator();
        }
        ui.printSeparator();
        parser = new Parser();
    }

    public String getResponse(String input) {
        String response;
        try {
            response = processInput(input);
        } catch (JimboException e) {
            response = "uh oh:\n" + e.getMessage();
        }
        return response;
    }

    private String processInput(String input) throws JimboException{
        Command command = parser.parse(input);
        String response = command.execute(ui, tasks, storage);
        if (command.shouldSave()) {
            storage.save(tasks.getInternalList());
        }
        if (command.shouldQuit()) {
            Platform.exit();
        }
        return response;
    }
}
