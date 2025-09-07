package saajid.parser;

import saajid.command.AddCommand;
import saajid.command.Command;
import saajid.command.DeleteCommand;
import saajid.command.ExitCommand;
import saajid.command.FindCommand;
import saajid.command.ListCommand;
import saajid.command.MarkCommand;
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
            if (words.length < 2) {
                throw new SaajidException("Please provide a task number to delete!");
            }
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        } else if (commandWord.equalsIgnoreCase("mark")) {
            if (words.length < 2) {
                throw new SaajidException("Please provide a task number to mark!");
            }
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        } else if (commandWord.equalsIgnoreCase("unmark")) {
            if (words.length < 2) {
                throw new SaajidException("Please provide a task number to unmark!");
            }
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        } else if (commandWord.equalsIgnoreCase("todo")) {
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SaajidException("The todo command must include a description.");
            }
            return new AddCommand(new Todo(words[1].trim()));
        } else if (commandWord.equalsIgnoreCase("deadline")) {
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
        } else if (commandWord.equalsIgnoreCase("event")) {
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
        } else if (commandWord.equalsIgnoreCase("find")) {
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new SaajidException("The find command must include a keyword.");
            }
            return new FindCommand(words[1].trim());
        } else {
            throw new SaajidException("I AM SORRY BUT I DO NOT UNDERSTAND WHAT THAT MEANS!");
        }
    }
}
