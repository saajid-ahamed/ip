import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

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
                    t = new Todo(desc);
                    break;
                case "D":
                    t = new Deadline(desc, parts[3].trim());
                    break;
                case "E":
                    t = new Event(desc, parts[3].trim(), parts[4].trim()); // store full time string
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

    /** Convert a Task into a saveable string. */
    private String formatTask(Task t) {
        if (t instanceof Todo) {
            return "T | " + (t.isDone ? "1" : "0") + " | " + t.description;
        } else if (t instanceof Deadline) {
            return "D | " + (t.isDone ? "1" : "0") + " | " + t.description
                    + " | " + ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            return "E | " + (t.isDone ? "1" : "0") + " | " + t.description
                    + " | " + ((Event) t).getFrom()
                    + " | " + ((Event) t).getTo();
        }
        return "";
    }
}
