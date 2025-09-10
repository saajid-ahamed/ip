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
            assert scanner != null;
        }

        /** Displays greeting message with lines. */
        public void greeting() {
            printStandardMessage(String.format("Hello! I'm %s\nWhat can I do for you?", Ui.NAME));
        }

        /** Displays exit message with lines. */
        public void exit() {
            printStandardMessage("Bye. Hope to see you soon!");
        }

        /** Reads next command input from user and trims. */
        public String nextCommand() {
            String input = scanner.nextLine().trim();
            assert input != null;
            return input;
        }
        /** Displays a horizontal line. */
        public void showLine() {
            System.out.println(Ui.HORIZONTAL_LINE);
        }
        /** Displays an error message with lines. */
        public void showError(String message) {
            printStandardMessage(message);
        }
        /** Displays a general message with lines. */
        public void showMessage(String message) {
            printStandardMessage(message);

          
        }
        /** Prints a line of message with no structure. */
        public void printLine(String message) {
            assert message != null;
            System.out.println(message);
        }

        /** Prints a message wrapped with horizontal lines. */
        private void printStandardMessage(String message) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(message);
            System.out.println("\n" + HORIZONTAL_LINE);
        }

    }
