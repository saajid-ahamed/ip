import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private final Scanner scanner;
    private static final String NAME = "Saajid";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greeting() {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", Ui.NAME));
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you soon!");
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    public String nextCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    public void showError(String message) {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    public void showMessage(String message) {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println("\n" + Ui.HORIZONTAL_LINE);
    }

    public void printLine(String message) {
        System.out.println(message);
    }

}
