package saajid.storage;

import saajid.exception.SaajidException;
import saajid.task.Deadline;
import saajid.task.Event;
import saajid.task.Task;
import saajid.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to save/load tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        assert this.filePath != null; //assuming that filepath is never null
        assert !filePath.isBlank();
    }

    /** Loads tasks from the file into memory. */
    public ArrayList<Task> load() throws SaajidException {
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Task> tasks1 = getTasks(tasks);
        if (tasks1 != null) {
            return tasks1;
        }
        return readTasksFromFile(tasks);
    }

    /**
     * Reads tasks from the existing file.
     *
     * @param tasks The task list to populate.
     * @return A populated list of tasks.
     * @throws SaajidException If an error occurs while reading.
     */
    private ArrayList<Task> readTasksFromFile(ArrayList<Task> tasks) throws SaajidException {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task t = parseTask(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            throw new SaajidException("Error reading data file: " + e.getMessage());
        }
        return tasks;
    }

    private ArrayList<Task> getTasks(ArrayList<Task> tasks) throws SaajidException {
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new SaajidException("Could not create data file: " + e.getMessage());
            }
            return tasks;
        }
        return null;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws SaajidException If there is an error writing to the file.
     */
    public void save(List<Task> tasks) throws SaajidException {
        assert tasks != null;
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task t : tasks) {
                writer.write(formatTask(t));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SaajidException("Error writing to data file: " + e.getMessage());
        }
    }

    /**
     * Converts a line of text into a Task object.
     *
     * @param line The line from the save file.
     * @return The parsed Task, or null if invalid.
     */
    private Task parseTask(String line) {
        assert line != null;
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String desc = parts[2].trim();
            Task t;
            t = createTask(type, desc, parts);
            if (t == null) return null; // corrupted line
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } catch (Exception e) {
            return null; // corrupted line
        }
    }

    /*
    AI-assisted:Refactor
    refactoring getRescheduleCommand into getRescheduleEventCommand and getRescheduleDeadlineCommand was assisted
    by AI (ChatGPT). The AI provided guidance on applying guard clauses,
    avoiding deep nesting, and following SLAP (Single Level of Abstraction Principle).
    The code  reviewed, tested, and integrated the suggestions into the final code and implemented it.
    */

    /**
     * Creates a Task from the given parts.
     *
     * @param type  The type of task ("T", "D", "E").
     * @param desc  The description of the task.
     * @param parts The split line from the save file.
     * @return A Task object, or null if type is invalid.
     */
    private static Task createTask(String type, String desc, String[] parts) {
        Task t;
        switch (type) {
            case "T":
                t = createTodoTask(desc);
                break;
            case "D":
                t = createDeadlineTask(parts, desc);
                break;
            case "E":
                t = createEventTask(parts, desc);
                break;
            default:
                return null;
        }
        return t;
    }

    //All of these refactored method created through IDE refactor method
    
    /*
    AI-assisted: Javadoc comments
    AI provided me with the initial content to include in the javadoc comments for the following refactored methods.
    AI provided a draft of these comments as well. Comments were then refined and implemented.
     */
    
    /**
     * Creates an Event task from the given parts.
     *
     * @param parts The split line from the save file.
     * @param desc  The task description.
     * @return An Event task.
     */
    private static Task createEventTask(String[] parts, String desc) {
        Task t;
        LocalDateTime from = LocalDateTime.parse(parts[3].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse(parts[4].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        t = new Event(desc, from, to);
        return t;
    }

    /**
     * Creates a Deadline task from the given parts.
     *
     * @param parts The split line from the save file.
     * @param desc  The task description.
     * @return A Deadline task.
     */
    private static Task createDeadlineTask(String[] parts, String desc) {
        Task t;
        LocalDateTime by = LocalDateTime.parse(parts[3].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        t = new Deadline(desc, by);
        return t;
    }

    /**
     * Creates a Todo task from the given description.
     *
     * @param desc The task description.
     * @return A Todo task.
     */
    private static Task createTodoTask(String desc) {
        Task t;
        t = new Todo(desc);
        return t;
    }

    /**
     * Converts a Task object into a string format for saving.
     *
     * @param t The task to format.
     * @return A string representation of the task.
     */
    private String formatTask(Task t) {
        assert t != null;
        if (t instanceof Todo) {
            return formatTodo(t);
        } else if (t instanceof Deadline) {
            return formatDeadline(t);
        } else if (t instanceof Event) {
            return formatEvent(t);
        }
        return "";
    }

    /**
     * Formats a Todo task into a saveable string.
     *
     * @param t The Todo task.
     * @return The formatted string.
     */
    private static String formatTodo(Task t) {
        return "T | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription();
    }

    /**
     * Formats an Event task into a saveable string.
     *
     * @param t The Event task.
     * @return The formatted string.
     */
    private static String formatEvent(Task t) {
        return "E | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription()
                + " | " + ((Event) t).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " | " + ((Event) t).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Formats a Deadline task into a saveable string.
     *
     * @param t The Deadline task.
     * @return The formatted string.
     */
    private static String formatDeadline(Task t) {
        return "D | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription()
                + " | " + ((Deadline) t).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
