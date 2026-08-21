import tasks.Deadline;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jimbo {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<Task>();

    private static void printSeparator() {
        System.out.println("--------------------");
    }

    private static void greet() {
        System.out.println("hi i'm jimbo");
        System.out.println("nice to meet you");
        printSeparator();
    }

    private static void sayBye() {
        System.out.println("bye bye");
        printSeparator();
    }

    private static String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    private static void echo(String toEcho) {
        System.out.println(toEcho);
        printSeparator();
    }

    private static void storeTask(Task task) {
        tasks.add(task);
        System.out.println("added task: " + task);
        printSeparator();
    }

    private static void markTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("that's not a valid task :(");
            printSeparator();
            return;
        }
        Task task = tasks.get(index);
        task.mark();
        System.out.println("marked the following task as done: ");
        System.out.println(task);
        printSeparator();
    }

    private static void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("that's not a valid task :(");
            printSeparator();
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        System.out.println("unmarked the following task: ");
        System.out.println(task);
        printSeparator();
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks stored");
            printSeparator();
            return;
        }

        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        printSeparator();
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
    public static void main(String[] args) {
        greet();
        while (true) {
            String input = getInput();
            boolean shouldQuit = false;
            String[] inputFragments = input.split(" ");
            switch (inputFragments[0]) {
                case "bye":
                    shouldQuit = true;
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    try {
                        var taskIndex = Integer.parseInt(inputFragments[1]);
                        // Since the list displayed to the user is 1-indexed, we need to
                        // change it back to 0-indexing
                        markTask(taskIndex - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("that doesn't seem like a number... please provide a task number");
                    }
                    break;
                case "unmark":
                    try {
                        var taskIndex = Integer.parseInt(inputFragments[1]);
                        // Since the list displayed to the user is 1-indexed, we need to
                        // change it back to 0-indexing
                        unmarkTask(taskIndex - 1);
                    } catch (NumberFormatException e) {
                        System.out.println("that doesn't seem like a number... please provide a task number");
                    }
                    break;
                case "todo":
                    storeTask(new Todo(String.join(" ",
                            Arrays.copyOfRange(inputFragments, 1, inputFragments.length))));
                    break;
                case "deadline":
                    List<String> commandSections = parseCommand(
                            Arrays.copyOfRange(inputFragments, 1, inputFragments.length));
                    if (commandSections.size() < 2) {
                        System.out.println("expected 2 parameters for command");
                        break;
                    }
                    if (commandSections.size() > 2) {
                        System.out.println("too many parameters given");
                        break;
                    }
                    String byFlag = "/by ";
                    String byParam = commandSections.get(1);
                    if (byParam.indexOf(byFlag) == -1) {
                        System.out.println("no by flag found");
                        break;
                    }
                    String byDate = byParam.substring(byParam.indexOf(byFlag) + byFlag.length());
                    storeTask(new Deadline(commandSections.get(0), byDate));
                    break;
                default:
                    storeTask(new Task(input));
            }
            if (shouldQuit) {
                break;
            }
        }
        sayBye();
    }
}
