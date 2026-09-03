package jimbo;

import jimbo.tasks.Deadline;
import jimbo.tasks.Event;
import jimbo.tasks.Task;
import jimbo.tasks.TaskSaver;
import jimbo.tasks.Todo;

import jimbo.Ui;


import jimbo.JimboException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jimbo {
    private List<Task> tasks = new ArrayList<Task>();
    private Ui ui = new Ui();

    private void storeTask(Task task) {
        tasks.add(task);
        System.out.println("added task: " + task);
        ui.printSeparator();
    }

    private void markTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("that's not a valid task :(");
            ui.printSeparator();
            return;
        }
        Task task = tasks.get(index);
        task.mark();
        System.out.println("marked the following task as done: ");
        System.out.println(task);
        ui.printSeparator();
    }

    private void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("that's not a valid task :(");
            ui.printSeparator();
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        System.out.println("unmarked the following task: ");
        System.out.println(task);
        ui.printSeparator();
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks stored");
            ui.printSeparator();
            return;
        }

        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        ui.printSeparator();
    }

    private static List<String> parseCommand(String[] commandFragments) {
        List<String> commandSections = new ArrayList<String>();
        String section = "";
        for (String fragment : commandFragments) {
            if (fragment.length() != 0 && fragment.charAt(0) != '/') {
                section += " " + fragment;

            } else {
                commandSections.add(section);
                section = fragment;
            }
        }
        commandSections.add(section);
        return commandSections;
    }

    private void deleteTask(int index) {
        Task toDelete = tasks.get(index);
        tasks.remove(index);
        System.out.println("deleted the following task: ");
        System.out.println(toDelete);
        ui.printSeparator();
    }

    private void saveTasks() throws JimboException {
        try {
            TaskSaver.save(tasks);
            System.out.println("saved tasks to file");
            ui.printSeparator();
        } catch (IOException e) {
            throw new JimboException("error while saving");
        }
    }

    private void loadTasks() {
        try {
            System.out.println("trying to load saved tasks...");
            tasks = TaskSaver.load();
            System.out.println("tasks loaded! " + tasks.size() + " tasks found");
        } catch (JimboException e) {
            System.out.println(e.getMessage());
        }
        ui.printSeparator();
    }

    public static void main(String[] args) {
        Jimbo jimbo = new Jimbo();
        jimbo.loadTasks();
        while (true) {
            String input = jimbo.ui.getInput();
            boolean shouldQuit = false;
            String[] inputFragments = input.split(" ");
            List<String> commandSections = parseCommand(
                    Arrays.copyOfRange(inputFragments, 1, inputFragments.length));
            try {
                switch (inputFragments[0]) {
                    case "bye":
                        shouldQuit = true;
                        break;
                    case "list":
                        jimbo.listTasks();
                        break;
                    case "mark":
                        try {
                            var taskIndex = Integer.parseInt(inputFragments[1]);
                            // Since the list displayed to the user is 1-indexed, we need to
                            // change it back to 0-indexing
                            jimbo.markTask(taskIndex - 1);
                        } catch (NumberFormatException e) {
                            throw new JimboException("please provide a number");
                        }
                        jimbo.saveTasks();
                        break;
                    case "unmark":
                        try {
                            var taskIndex = Integer.parseInt(inputFragments[1]);
                            // Since the list displayed to the user is 1-indexed, we need to
                            // change it back to 0-indexing
                            jimbo.unmarkTask(taskIndex - 1);
                        } catch (NumberFormatException e) {
                            throw new JimboException("please provide a number");
                        }
                        jimbo.saveTasks();
                        break;
                    case "todo":
                        jimbo.storeTask(new Todo(String.join(" ",
                                Arrays.copyOfRange(inputFragments, 1, inputFragments.length))));
                        jimbo.saveTasks();
                        break;
                    case "deadline":
                        if (commandSections.size() < 2) {
                            throw new JimboException("expected 2 parameters for commands");
                        }
                        if (commandSections.size() > 2) {
                            throw new JimboException("too many parameters given");
                        }
                        String byFlag = "/by ";
                        String byParam = commandSections.get(1);
                        if (byParam.indexOf(byFlag) == -1) {
                            throw new JimboException("no /by flag found");
                        }
                        String byDate = byParam.substring(byParam.indexOf(byFlag) + byFlag.length());
                        jimbo.storeTask(new Deadline(commandSections.get(0), byDate));
                        jimbo.saveTasks();
                        break;
                    case "event":
                        if (commandSections.size() < 3) {
                            throw new JimboException("expected 3 parameters for commands");
                        }
                        if (commandSections.size() > 3) {
                            throw new JimboException("too many parameters given");
                        }
                        String fromFlag = "/from ";
                        String fromParam = commandSections.get(1);
                        if (fromParam.indexOf(fromFlag) == -1) {
                            throw new JimboException("no /from flag found");
                        }
                        String fromDate = fromParam.substring(fromParam.indexOf(fromFlag) + fromFlag.length());

                        String toFlag = "/to ";
                        String toParam = commandSections.get(2);
                        if (toParam.indexOf(toFlag) == -1) {
                            throw new JimboException("no /to flag found");
                        }
                        String toDate = toParam.substring(toParam.indexOf(toFlag) + toFlag.length());
                        jimbo.storeTask(new Event(commandSections.get(0), fromDate, toDate));
                        jimbo.saveTasks();
                        break;
                    case "delete":
                        try {
                            var taskIndex = Integer.parseInt(inputFragments[1]);
                            // Since the list displayed to the user is 1-indexed, we need to
                            // change it back to 0-indexing
                            jimbo.deleteTask(taskIndex - 1);
                        } catch (NumberFormatException e) {
                            throw new JimboException("please provide a number");
                        }
                        jimbo.saveTasks();
                        break;
                    case "save":
                        jimbo.saveTasks();
                        break;
                    default:
                        throw new JimboException("i don't understand this command");
                }
            } catch (JimboException e) {
                System.out.println(e.getMessage());
                jimbo.ui.printSeparator();
            }
            if (shouldQuit) {
                break;
            }
        }
        jimbo.ui.sayBye();
    }
}
