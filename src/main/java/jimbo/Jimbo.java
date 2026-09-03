package jimbo;

import jimbo.command.Command;

public class Jimbo {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

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
}
