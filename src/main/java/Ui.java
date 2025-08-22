import java.util.Scanner;
/**
 * Handles all user interactions including displaying messages,
 * greetings, errors, and reading user input.
 */
public class Ui {
    //standard variables that will be used
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private final Scanner scanner;
    private static final String NAME = "Saajid";

    //to allow for a GUI
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // function to print standard greeting message when one enters the chatbot
    public void greeting() {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", Ui.NAME));
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    // function to print standard goodbye message when one leaves the chatbot
    public void exit() {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you soon!");
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    // provides next command entered by user
    public String nextCommand() {
        return scanner.nextLine().trim();
    }

    // function to print a line of dashes
    public void showLine() {
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    // print an error message with formatting
    public void showError(String message) {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    // print any message with formatting
    public void showMessage(String message) {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    public void printLine(String message) {
        System.out.println(message);
    }

}
