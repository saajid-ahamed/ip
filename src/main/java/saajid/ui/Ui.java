    package saajid.ui;

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

        /** Constructs a Ui object for user interaction. */
        public Ui() {
            this.scanner = new Scanner(System.in);
        }

        /** Displays greeting message with lines. */
        public void greeting() {
            System.out.println(Ui.HORIZONTAL_LINE);
            System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", Ui.NAME));
            System.out.println("\n" + Ui.HORIZONTAL_LINE);
        }

        /** Displays exit message with lines. */
        public void exit() {
            System.out.println(Ui.HORIZONTAL_LINE);
            System.out.println("Bye. Hope to see you soon!");
            System.out.println("\n" + Ui.HORIZONTAL_LINE);
        }

        /** Reads next command input from user and trims. */
        public String nextCommand() {
            return scanner.nextLine().trim();
        }
        /** Displays a horizontal line. */
        public void showLine() {
            System.out.println(Ui.HORIZONTAL_LINE);
        }
        /** Displays an error message with lines. */
        public void showError(String message) {
            System.out.println(Ui.HORIZONTAL_LINE);
            System.out.println(message);
            System.out.println("\n" + Ui.HORIZONTAL_LINE);
        }
        /** Displays a general message with lines. */
        public void showMessage(String message) {
            System.out.println(Ui.HORIZONTAL_LINE);
            System.out.println(message);
            System.out.println("\n" + Ui.HORIZONTAL_LINE);
        }
        /** Prints a line of message with no structure. */
        public void printLine(String message) {
            System.out.println(message);
        }

    }
