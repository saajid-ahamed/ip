import java.util.ArrayList;
import java.util.Scanner;
// More oop already implemented

public class Saajid {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    public Saajid() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/saajid.txt");

        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (SaajidException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            loaded = new TaskList();
        }
        this.tasks = loaded;
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
                command.execute(tasks, ui);
                storage.save(tasks.getTasks()); // Execute command
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
