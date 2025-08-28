package saajid.task;

/**
 * Represents a to-do task without specific date/time.
 */
public class Todo extends Task {

    /** Constructs a Todo with the given string description. */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

