import java.util.Scanner;

public class Jimbo {
    private static final Scanner scanner = new Scanner(System.in);

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

    public static void main(String[] args) {
        greet();
        while (true) {
            String input = getInput();
            if (input.equals("bye")) {
                break;
            }
            echo(input);
        }
        sayBye();
    }
}
