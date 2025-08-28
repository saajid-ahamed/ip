package saajid.ui;

import saajid.command.Command;
import saajid.exception.SaajidException;
import saajid.parser.Parser;
import saajid.storage.Storage;
import saajid.task.TaskList;

/**
 * Entry point for the Saajid chatbot application.
 */
public class Saajid {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    /** Constructs a Saajid chatbot instance. */
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
     * The loop continues until the user enters a command
     * that signals program termination (e.g., "bye").
     * Each user input is parsed into a saajid.command.Command object
     * which is then executed.
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

    /** Main method to start the application. */
    public static void main(String[] args) {
        new Saajid().run();
    }
}
