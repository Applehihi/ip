package jimbo;

import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public void printSeparator() {
        System.out.println("--------------------");
    }

    public void greet() {
        System.out.println("hi i'm jimbo");
        System.out.println("nice to meet you");
        printSeparator();
    }

    public void sayBye() {
        System.out.println("bye bye");
    }

    public void echo(String toEcho) {
        System.out.println(toEcho);
        printSeparator();
    }

    public String getInput() {
        System.out.print("> ");
        return SCANNER.nextLine();
    }
}
