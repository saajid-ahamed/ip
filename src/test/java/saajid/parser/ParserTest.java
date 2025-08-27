package saajid.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saajid.command.*;
import saajid.exception.SaajidException;
import saajid.task.Deadline;
import saajid.task.Event;
import saajid.task.Todo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void parseExitCommand() throws SaajidException {
        assertEquals(ExitCommand.class, parser.parse("bye").getClass());
        assertEquals(ExitCommand.class, parser.parse("BYE").getClass()); // case-insensitive
    }

    @Test
    void parseListCommand() throws SaajidException {
        assertEquals(ListCommand.class, parser.parse("list").getClass());
    }

    @Test
    void parseDeleteCommand_valid() throws SaajidException {
        DeleteCommand command = (DeleteCommand) parser.parse("delete 3");
        assertEquals(2, command.getIndex()); // parser uses zero-based indexing
    }

    @Test
    void parseDeleteCommand_missingIndex() {
        assertThrows(SaajidException.class, () -> parser.parse("delete"));
    }

    @Test
    void parseMarkCommand_valid() throws SaajidException {
        MarkCommand command = (MarkCommand) parser.parse("mark 5");
        assertEquals(4, command.getIndex());
    }

    @Test
    void parseMarkCommand_missingIndex() {
        assertThrows(SaajidException.class, () -> parser.parse("mark"));
    }

    @Test
    void parseUnmarkCommand_valid() throws SaajidException {
        UnmarkCommand command = (UnmarkCommand) parser.parse("unmark 1");
        assertEquals(0, command.getIndex());
    }

    @Test
    void parseUnmarkCommand_missingIndex() {
        assertThrows(SaajidException.class, () -> parser.parse("unmark"));
    }

    @Test
    void parseTodoCommand_valid() throws SaajidException {
        AddCommand command = (AddCommand) parser.parse("todo Read book");
        assertEquals(Todo.class, command.getTask().getClass());
        assertEquals("Read book", command.getTask().getDescription());
    }

    @Test
    void parseTodoCommand_missingDescription() {
        assertThrows(SaajidException.class, () -> parser.parse("todo"));
        assertThrows(SaajidException.class, () -> parser.parse("todo   "));
    }

    @Test
    void parseDeadlineCommand_valid() throws SaajidException {
        AddCommand command = (AddCommand) parser.parse("deadline Submit /by 2025-12-31 2359");
        assertEquals(Deadline.class, command.getTask().getClass());
        assertEquals("Submit", command.getTask().getDescription());
        Deadline deadline = (Deadline) command.getTask();
        assertEquals(LocalDateTime.of(2025, 12, 31, 23, 59), deadline.getBy());
    }

    @Test
    void parseDeadlineCommand_missingBy() {
        assertThrows(SaajidException.class, () -> parser.parse("deadline Submit"));
        assertThrows(SaajidException.class, () -> parser.parse("deadline Submit /bywrongformat"));
    }

    @Test
    void parseDeadlineCommand_invalidDate() {
        assertThrows(SaajidException.class, () -> parser.parse("deadline Submit /by 2025-12-31 99:99"));
    }

    @Test
    void parseEventCommand_valid() throws SaajidException {
        AddCommand command = (AddCommand) parser.parse(
                "event Party /from 2025-01-01 1800 /to 2025-01-01 2100");
        assertEquals(Event.class, command.getTask().getClass());
        Event event = (Event) command.getTask();
        assertEquals("Party", event.getDescription());
        assertEquals(LocalDateTime.of(2025, 1, 1, 18, 0), event.getFrom());
        assertEquals(LocalDateTime.of(2025, 1, 1, 21, 0), event.getTo());
    }

    @Test
    void parseEventCommand_missingParts() {
        assertThrows(SaajidException.class, () -> parser.parse("event Party"));
        assertThrows(SaajidException.class, () -> parser.parse("event Party /from 2025-01-01 1800"));
        assertThrows(SaajidException.class, () -> parser.parse("event Party /to 2025-01-01 2100"));
    }

    @Test
    void parseEventCommand_invalidDates() {
        assertThrows(SaajidException.class, () -> parser.parse(
                "event Party /from 2025-01-01 1800 /to invalid"));
    }

    @Test
    void parseUnknownCommand() {
        assertThrows(SaajidException.class, () -> parser.parse("foobar"));
    }

}
