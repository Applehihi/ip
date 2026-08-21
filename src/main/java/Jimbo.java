import java.util.ArrayList;
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

    public static void main(String[] args) {
        greet();
        while (true) {
            String input = getInput();
            if (input.equals("bye")) {
                break;
            }
            storeTask(new Task(input));
        }
        sayBye();
    }
}
