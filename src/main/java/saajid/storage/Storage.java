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
    }

    /** Loads tasks from the file into memory. */
    public ArrayList<Task> load() throws SaajidException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new SaajidException("Could not create data file: " + e.getMessage());
            }
            return tasks;
        }

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

    /** Saves the current list of tasks to the file. */
    public void save(List<Task> tasks) throws SaajidException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task t : tasks) {
                writer.write(formatTask(t));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SaajidException("Error writing to data file: " + e.getMessage());
        }
    }

    /** Parse a line into a Task object. */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String desc = parts[2].trim();
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
                    return null; // corrupted line
            }
            if (isDone) t.markAsDone();
            return t;
        } catch (Exception e) {
            return null; // corrupted line
        }
    }
    
    //All of these refactored method created through IDE refactor method

    private static Task createEventTask(String[] parts, String desc) {
        Task t;
        LocalDateTime from = LocalDateTime.parse(parts[3].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse(parts[4].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        t = new Event(desc, from, to);
        return t;
    }

    private static Task createDeadlineTask(String[] parts, String desc) {
        Task t;
        LocalDateTime by = LocalDateTime.parse(parts[3].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        t = new Deadline(desc, by);
        return t;
    }

    private static Task createTodoTask(String desc) {
        Task t;
        t = new Todo(desc);
        return t;
    }

    /** Convert a Task into a saveable string. */
    private String formatTask(Task t) {
        if (t instanceof Todo) {
            return "T | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription();
        } else if (t instanceof Deadline) {
            return "D | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription()
                    + " | " + ((Deadline) t).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (t instanceof Event) {
            return "E | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription()
                    + " | " + ((Event) t).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    + " | " + ((Event) t).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }
        return "";
    }
}
