package saajid.ui;

import saajid.command.Command;
import saajid.exception.InvalidCommandException;
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

        //all variables are assumed to not be null
        assert this.ui != null;
        assert this.parser != null;
        assert this.storage != null;

        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (InvalidCommandException e) {
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
            isExit = handleUserInput(input);
        }
    }

    /**
     * Handles a single line of user input by parsing and executing the corresponding {@link Command}.
     * Saves the updated task list to storage after execution.
     *
     * @param input The raw user input string.
     * @return true if the command signals application exit (e.g., "bye"), false otherwise.
     */
    private boolean handleUserInput(String input) {
        try {
            Command command = parser.parse(input);
            command.execute(tasks, ui);
            storage.save(tasks.getTasks());
            return command.isExit();
        } catch (InvalidCommandException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    /**
     * Processes a single user input and returns the response as a string.
     * This method is intended for programmatic use (e.g., testing or GUI integration),
     * unlike {@link #handleUserInput(String)} which interacts directly with the console UI.
     *
     * @param input The raw user input string.
     * @return The chatbot's response as a string, or an error message if parsing fails.
     */
    public String processInput(String input) {
        StringUi stringUi = new StringUi();
        try {
            Command command = parser.parse(input);
            assert command != null;
            command.execute(tasks, stringUi);
            storage.save(tasks.getTasks());
            return stringUi.getOutput();
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

    /** Main method to start the application. */
    public static void main(String[] args) {
        new Saajid().run();
    }
}
