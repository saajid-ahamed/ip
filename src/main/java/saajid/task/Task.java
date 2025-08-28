package saajid.task;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /** Constructs a Task with the given description. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /** Returns the status icon (X for done, space otherwise). */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /** Marks this task as done. */
    public void markAsDone() {
        this.isDone = true;
    }
    /** Marks this task as not done. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
