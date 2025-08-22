import java.util.ArrayList;
import java.util.Scanner;

public class Saajid {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;

    public Saajid() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser();
    }
    /**
     * Runs the main command loop of the application.
     * <p>
     * The loop continues until the user enters a command
     * that signals program termination (e.g., "bye").
     * Each user input is parsed into a Command object
     * which is then executed.
     * </p>
     */
    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.nextCommand();
            try {
                Command command = parser.parse(input); // Parse into command
                command.execute(tasks, ui);  // Execute command
                isExit = command.isExit();   // Check if exit
            } catch (SaajidException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Saajid().run();
    }
}
