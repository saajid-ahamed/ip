package saajid.parser;

import saajid.command.AddCommand;
import saajid.command.Command;
import saajid.command.DeleteCommand;
import saajid.command.ExitCommand;
import saajid.command.FindCommand;
import saajid.command.ListCommand;
import saajid.command.MarkCommand;
import saajid.command.RescheduleCommand;
import saajid.command.UnmarkCommand;
import saajid.exception.InvalidCommandException;
import saajid.task.Deadline;
import saajid.task.Event;
import saajid.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input strings and converts them into {@link Command} objects.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /**
     * Parses a given input string into a {@link Command}.
     *
     * @param input The full user input string.
     * @return The corresponding command.
     * @throws InvalidCommandException If input cannot be parsed into a valid command.
     */
    public Command parse(String input) throws InvalidCommandException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        if (commandWord.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (commandWord.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (commandWord.equalsIgnoreCase("delete")) {
            return getDeleteCommand(words);
        } else if (commandWord.equalsIgnoreCase("mark")) {
            return getMarkCommand(words);
        } else if (commandWord.equalsIgnoreCase("unmark")) {
            return getUnmarkCommand(words);
        } else if (commandWord.equalsIgnoreCase("todo")) {
            return getAddTodoCommand(words);
        } else if (commandWord.equalsIgnoreCase("deadline")) {
            return getAddDeadlineCommand(words);
        } else if (commandWord.equalsIgnoreCase("event")) {
            return getAddEventCommand(words);
        } else if (commandWord.equalsIgnoreCase("find")) {
            return getFindCommand(words);
        } else if (commandWord.equalsIgnoreCase("reschedule")) {
            return getRescheduleCommand(words);
        } else {
            throw new InvalidCommandException("I AM SORRY BUT I DO NOT UNDERSTAND WHAT THAT MEANS!");
        }
    }

    // Added all of these private methods through the use if IDE refactor method

    /**
     * Creates a {@link FindCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the keyword.
     * @return A FindCommand with the given keyword.
     * @throws InvalidCommandException If no keyword is provided.
     */
    private static FindCommand getFindCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new InvalidCommandException("The find command must include a keyword.");
        }
        return new FindCommand(words[1].trim());
    }

    /**
     * Creates an {@link AddCommand} containing an {@link Event}.
     *
     * @param words The split input string, expected format: description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm.
     * @return An AddCommand wrapping a new Event.
     * @throws InvalidCommandException If the input is missing required parts or date-time is invalid.
     */
    private static AddCommand getAddEventCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
            throw new InvalidCommandException("The event command must include a description, " +
                            "/from and /to date-times."
            );
        }
        String[] parts = words[1].split("/from", 2);
        String desc = parts[0].trim();

        String[] timeParts = parts[1].split("/to", 2);
        String fromStr = timeParts[0].trim();
        String toStr = timeParts[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
            LocalDateTime to = LocalDateTime.parse(toStr, formatter);
            return new AddCommand(new Event(desc, from, to));
        } catch (Exception e) {
            throw new InvalidCommandException("Please enter /from and /to in yyyy-MM-dd HHmm format," +
                    "e.g., /from 2019-12-06 1400 /to 2019-12-06 1600");
        }
    }

    /**
     * Creates an {@link AddCommand} containing a {@link Deadline}.
     *
     * @param words The split input string, expected format: description /by yyyy-MM-dd HHmm.
     * @return An AddCommand wrapping a new Deadline.
     * @throws InvalidCommandException If input is missing required parts or date-time is invalid.
     */
    private static AddCommand getAddDeadlineCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new InvalidCommandException("The deadline command must include a description and a /by date-time.");
        }
        String[] parts = words[1].split("/by", 2);
        String desc = parts[0].trim();
        String dateTimeStr = parts[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(dateTimeStr, formatter);
            return new AddCommand(new Deadline(desc, by));
        } catch (Exception e) {
            throw new InvalidCommandException("Please enter date and time in yyyy-MM-dd HHmm format," +
                    "e.g., 2019-12-02 1800");
        }
    }

    /**
     * Creates an {@link AddCommand} containing a {@link Todo}.
     *
     * @param words The split input string, where words[1] is the description.
     * @return An AddCommand wrapping a new Todo.
     * @throws InvalidCommandException If description is missing or empty.
     */
    private static AddCommand getAddTodoCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new InvalidCommandException("The todo command must include a description.");
        }
        return new AddCommand(new Todo(words[1].trim()));
    }

    /**
     * Creates an {@link UnmarkCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the task number.
     * @return An UnmarkCommand for the specified task.
     * @throws InvalidCommandException If task number is missing.
     */
    private static UnmarkCommand getUnmarkCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2) {
            throw new InvalidCommandException("Please provide a task number to unmark!");
        }
        return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
    }

    /**
     * Creates a {@link MarkCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the task number.
     * @return A MarkCommand for the specified task.
     * @throws InvalidCommandException If task number is missing.
     */
    private static MarkCommand getMarkCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2) {
            throw new InvalidCommandException("Please provide a task number to mark!");
        }
        return new MarkCommand(Integer.parseInt(words[1]) - 1);
    }

    /**
     * Creates a {@link DeleteCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the task number.
     * @return A DeleteCommand for the specified task.
     * @throws InvalidCommandException If task number is missing.
     */
    private static DeleteCommand getDeleteCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2) {
            throw new InvalidCommandException("Please provide a task number to delete!");
        }
        return new DeleteCommand(Integer.parseInt(words[1]) - 1);
    }

    /*
    AI-assisted:Refactor
    refactoring getRescheduleCommand into getRescheduleEventCommand and getRescheduleDeadlineCommand was assisted
    by AI (ChatGPT). The AI provided guidance on applying guard clauses,
    avoiding deep nesting, and following SLAP (Single Level of Abstraction Principle).
    The code was then reviewed, tested, and integrated into the final code and implemented it.
    */

    /**
     * Parses input and creates a {@link RescheduleCommand}.
     * Supports rescheduling of deadlines (/by) and events (/from ... /to).
     *
     * @param words User input split into command and details.
     * @return A RescheduleCommand for the given task.
     * @throws InvalidCommandException If format is invalid or details are missing.
     */
    private static RescheduleCommand getRescheduleCommand(String[] words) throws InvalidCommandException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new InvalidCommandException("Reschedule command requires: taskIndex and new date/time.");
        }
        String[] parts = words[1].split(" ", 2);
        if (parts.length < 2) {
            throw new InvalidCommandException("Please provide task index and new schedule details.");
        }
        int taskIndex = Integer.parseInt(parts[0]) - 1;
        String details = parts[1].trim();
        if (details.contains("/by")) {
            return getRescheduleDeadlineCommand(details, taskIndex);
        } else if (details.contains("/from") && details.contains("/to")) {
            return getRescheduleEventCommand(details, taskIndex);
        } else {
            throw new InvalidCommandException("Reschedule requires /by for deadlines or /from and /to for events.");
        }
    }

    /*
    AI-assisted: Javadoc comments
    AI provided me with the initial content to include in the javadoc comments for the following refactored methods.
    AI provided a draft of these comments as well. Comments were then refined and implemented.
     */

    /**
     * Creates a {@link RescheduleCommand} for an {@link saajid.task.Event}.
     * <p>
     * Expected input format:
     * <code>/from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm</code>.
     *
     * @param details   The schedule details provided by the user.
     * @param taskIndex The zero-based index of the task to be rescheduled.
     * @return A {@link RescheduleCommand} with updated start and end times.
     * @throws InvalidCommandException If parsing of date-time fails or input format is invalid.
     */
    private static RescheduleCommand getRescheduleEventCommand(String details,
                                                               int taskIndex) throws InvalidCommandException {
        String[] timeParts = details.split("/from", 2)[1].split("/to", 2);
        String fromStr = timeParts[0].trim();
        String toStr = timeParts[1].trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toStr, FORMATTER);
            return new RescheduleCommand(taskIndex, from, to);
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid datetime format. Use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Creates a {@link RescheduleCommand} for a {@link saajid.task.Deadline}.
     * <p>
     * Expected input format:
     * <code>/by yyyy-MM-dd HHmm</code>.
     *
     * @param details   The schedule details provided by the user.
     * @param taskIndex The zero-based index of the task to be rescheduled.
     * @return A {@link RescheduleCommand} with updated deadline.
     * @throws InvalidCommandException If parsing of date-time fails or input format is invalid.
     */
    private static RescheduleCommand getRescheduleDeadlineCommand(String details,
                                                                  int taskIndex) throws InvalidCommandException {
        String newByStr = details.split("/by", 2)[1].trim();
        try {
            LocalDateTime newBy = LocalDateTime.parse(newByStr, FORMATTER);
            return new RescheduleCommand(taskIndex, newBy);
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid datetime format. Use yyyy-MM-dd HHmm.");
        }
    }
}