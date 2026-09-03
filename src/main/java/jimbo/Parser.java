package jimbo;

import jimbo.command.*;
import jimbo.tasks.Deadline;
import jimbo.tasks.Event;
import jimbo.tasks.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public Command parse(String input) throws JimboException {
        String[] inputFragments = input.split(" ");
        List<String> commandSections = splitCommandIntoSections(
                Arrays.copyOfRange(inputFragments, 1, inputFragments.length));
        // make it case-insensitive
        String commandType = inputFragments[0].toLowerCase();
        switch (commandType) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "save":
                return new SaveCommand();
            case "mark":
                try {
                    var taskIndex = Integer.parseInt(inputFragments[1]);
                    // Since the list displayed to the user is 1-indexed, we need to
                    // change it back to 0-indexing
                    return new MarkCommand(taskIndex - 1);
                } catch (NumberFormatException e) {
                    throw new JimboException("please provide a number");
                }
            case "unmark":
                try {
                    var taskIndex = Integer.parseInt(inputFragments[1]);
                    // Since the list displayed to the user is 1-indexed, we need to
                    // change it back to 0-indexing
                    return new UnmarkCommand(taskIndex - 1);
                } catch (NumberFormatException e) {
                    throw new JimboException("please provide a number");
                }
            case "todo":
                return new TaskCommand(new Todo(String.join(" ",
                                Arrays.copyOfRange(inputFragments, 1, inputFragments.length))));
            case "deadline":
                if (commandSections.size() < 2) {
                    throw new JimboException("expected 2 parameters for commands");
                }
                if (commandSections.size() > 2) {
                    throw new JimboException("too many parameters given");
                }
                String byFlag = "/by ";
                String byParam = commandSections.get(1);
                if (!byParam.contains(byFlag)) {
                    throw new JimboException("no /by flag found");
                }
                String byDate = byParam.substring(byParam.indexOf(byFlag) + byFlag.length());
                return new TaskCommand(new Deadline(commandSections.get(0), byDate));
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
                return new TaskCommand(new Event(commandSections.get(0), fromDate, toDate));
            default:
                throw new JimboException("i don't understand this command");
        }
    }

    private List<String> splitCommandIntoSections(String[] commandFragments) {
        List<String> commandSections = new ArrayList<>();
        String section = "";
        for (String fragment : commandFragments) {
            if (!fragment.isEmpty() && fragment.charAt(0) != '/') {
                section += " " + fragment;
            } else {
                commandSections.add(section);
                section = fragment;
            }
        }
        commandSections.add(section);
        return commandSections;
    }

}
