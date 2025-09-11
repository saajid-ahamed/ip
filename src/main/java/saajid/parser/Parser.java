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
import saajid.exception.SaajidException;
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
     * @throws SaajidException If input cannot be parsed into a valid command.
     */
    public Command parse(String input) throws SaajidException {
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
            throw new SaajidException("I AM SORRY BUT I DO NOT UNDERSTAND WHAT THAT MEANS!");
        }
    }

    // Added all of these private methods through the use if IDE refactor method

    /**
     * Creates a {@link FindCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the keyword.
     * @return A FindCommand with the given keyword.
     * @throws SaajidException If no keyword is provided.
     */
    private static FindCommand getFindCommand(String[] words) throws SaajidException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SaajidException("The find command must include a keyword.");
        }
        return new FindCommand(words[1].trim());
    }

    /**
     * Creates an {@link AddCommand} containing an {@link Event}.
     *
     * @param words The split input string, expected format: description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm.
     * @return An AddCommand wrapping a new Event.
     * @throws SaajidException If the input is missing required parts or date-time is invalid.
     */
    private static AddCommand getAddEventCommand(String[] words) throws SaajidException {
        if (words.length < 2 || !words[1].contains("/from") || !words[1].contains("/to")) {
            throw new SaajidException("The event command must include a description, /from and /to date-times.");
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
            throw new SaajidException("Please enter /from and /to in yyyy-MM-dd HHmm format," +
                    "e.g., /from 2019-12-06 1400 /to 2019-12-06 1600");
        }
    }

    /**
     * Creates an {@link AddCommand} containing a {@link Deadline}.
     *
     * @param words The split input string, expected format: description /by yyyy-MM-dd HHmm.
     * @return An AddCommand wrapping a new Deadline.
     * @throws SaajidException If input is missing required parts or date-time is invalid.
     */
    private static AddCommand getAddDeadlineCommand(String[] words) throws SaajidException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new SaajidException("The deadline command must include a description and a /by date-time.");
        }
        String[] parts = words[1].split("/by", 2);
        String desc = parts[0].trim();
        String dateTimeStr = parts[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(dateTimeStr, formatter);
            return new AddCommand(new Deadline(desc, by));
        } catch (Exception e) {
            throw new SaajidException("Please enter date and time in yyyy-MM-dd HHmm format," +
                    "e.g., 2019-12-02 1800");
        }
    }

    /**
     * Creates an {@link AddCommand} containing a {@link Todo}.
     *
     * @param words The split input string, where words[1] is the description.
     * @return An AddCommand wrapping a new Todo.
     * @throws SaajidException If description is missing or empty.
     */
    private static AddCommand getAddTodoCommand(String[] words) throws SaajidException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SaajidException("The todo command must include a description.");
        }
        return new AddCommand(new Todo(words[1].trim()));
    }

    /**
     * Creates an {@link UnmarkCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the task number.
     * @return An UnmarkCommand for the specified task.
     * @throws SaajidException If task number is missing.
     */
    private static UnmarkCommand getUnmarkCommand(String[] words) throws SaajidException {
        if (words.length < 2) {
            throw new SaajidException("Please provide a task number to unmark!");
        }
        return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
    }

    /**
     * Creates a {@link MarkCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the task number.
     * @return A MarkCommand for the specified task.
     * @throws SaajidException If task number is missing.
     */
    private static MarkCommand getMarkCommand(String[] words) throws SaajidException {
        if (words.length < 2) {
            throw new SaajidException("Please provide a task number to mark!");
        }
        return new MarkCommand(Integer.parseInt(words[1]) - 1);
    }

    /**
     * Creates a {@link DeleteCommand} from user input.
     *
     * @param words The split input string, where words[1] should contain the task number.
     * @return A DeleteCommand for the specified task.
     * @throws SaajidException If task number is missing.
     */
    private static DeleteCommand getDeleteCommand(String[] words) throws SaajidException {
        if (words.length < 2) {
            throw new SaajidException("Please provide a task number to delete!");
        }
        return new DeleteCommand(Integer.parseInt(words[1]) - 1);
    }

    private static RescheduleCommand getRescheduleCommand(String[] words) throws SaajidException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SaajidException("Reschedule command requires: taskIndex and new date/time.");
        }
        String[] parts = words[1].split(" ", 2);
        if (parts.length < 2) {
            throw new SaajidException("Please provide task index and new schedule details.");
        }
        int taskIndex = Integer.parseInt(parts[0]) - 1;
        String details = parts[1].trim();
        if (details.contains("/by")) {
            return getRescheduleDeadlineCommand(details, taskIndex);
        } else if (details.contains("/from") && details.contains("/to")) {
            return getRescheduleEventCommand(details, taskIndex);
        } else {
            throw new SaajidException("Reschedule requires /by for deadlines or /from and /to for events.");
        }
    }

    // Refactor methods created using IDE
    private static RescheduleCommand getRescheduleEventCommand(String details, int taskIndex) throws SaajidException {
        String[] timeParts = details.split("/from", 2)[1].split("/to", 2);
        String fromStr = timeParts[0].trim();
        String toStr = timeParts[1].trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toStr, FORMATTER);
            return new RescheduleCommand(taskIndex, from, to);
        } catch (Exception e) {
            throw new SaajidException("Invalid datetime format. Use yyyy-MM-dd HHmm.");
        }
    }

    private static RescheduleCommand getRescheduleDeadlineCommand(String details, int taskIndex) throws SaajidException {
        String newByStr = details.split("/by", 2)[1].trim();
        try {
            LocalDateTime newBy = LocalDateTime.parse(newByStr, FORMATTER);
            return new RescheduleCommand(taskIndex, newBy);
        } catch (Exception e) {
            throw new SaajidException("Invalid datetime format. Use yyyy-MM-dd HHmm.");
        }
    }
}