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

    /**
     * Starts accepting commands and executing them.
     */
    public void run() {
        boolean shouldQuit = false;

        while (!shouldQuit) {
            try {
                Command command = parser.parse(ui.getInput());
                command.execute(ui, tasks, storage);
                shouldQuit = command.shouldQuit();
                if (command.shouldSave()) {
                    storage.save(tasks.getInternalList());
                }
            } catch (JimboException e) {
                System.out.println(e.getMessage());
                ui.printSeparator();
            }
        }
        ui.sayBye();
    }

    public static void main(String[] args) {
        Jimbo jimbo = new Jimbo();
        jimbo.run();
    }

    public String getResponse(String input) {
        processInput(input);
        return "todo";
    }

    private void processInput(String input) {
        try {
            Command command = parser.parse(input);
            command.execute(ui, tasks, storage);
//            shouldQuit = command.shouldQuit();
            if (command.shouldSave()) {
                storage.save(tasks.getInternalList());
            }
            if (command.shouldQuit()) {
                Platform.exit();
            }
        } catch (JimboException e) {
            System.out.println(e.getMessage());
            ui.printSeparator();
        }
    }
}
