package saajid.command;

import saajid.exception.InvalidCommandException;
import saajid.task.Deadline;
import saajid.task.Event;
import saajid.task.Task;
import saajid.task.TaskList;
import saajid.ui.Ui;

import java.time.LocalDateTime;

/*
    AI-assisted: Javadoc comments
    AI provided me with the initial content to include in the javadoc comments for the following refactored methods.
    AI provided a draft of these comments as well. Comments were then refined and implemented.
     */

/**
 * Represents a command that reschedules a Deadline or Event task.
 */
public class RescheduleCommand extends Command {

    private final int taskIndex;
    private final LocalDateTime newFrom;
    private final LocalDateTime newTo;
    private final LocalDateTime newBy;

    /**
     * Constructs a RescheduleCommand for Deadline tasks.
     *
     * @param taskIndex Index of the task to reschedule.
     * @param newBy     New deadline date and time.
     */
    public RescheduleCommand(int taskIndex, LocalDateTime newBy) {
        this.taskIndex = taskIndex;
        this.newBy = newBy;
        this.newFrom = null;
        this.newTo = null;
    }

    /**
     * Constructs a RescheduleCommand for Event tasks.
     *
     * @param taskIndex Index of the task to reschedule.
     * @param newFrom   New starting date and time.
     * @param newTo     New ending date and time.
     */
    public RescheduleCommand(int taskIndex, LocalDateTime newFrom, LocalDateTime newTo) {
        this.taskIndex = taskIndex;
        this.newFrom = newFrom;
        this.newTo = newTo;
        this.newBy = null;
    }

    /**
     * Reschedules the specified task if it is a Deadline or Event.
     *
     * @param tasks the TaskList containing the task
     * @param ui the UI to display updates
     * @throws InvalidCommandException if the task index is invalid or cannot be rescheduled
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidCommandException("Invalid task index. Please select a valid task.");
        }
        Task task = tasks.getTask(taskIndex);
        if (task instanceof Deadline && newBy != null) {
            tasks.rescheduleTask(ui, (Deadline) task, newBy);
        } else if (task instanceof Event && newFrom != null && newTo != null) {
            tasks.rescheduleTask(ui, (Event) task, newFrom, newTo);
        } else {
            throw new InvalidCommandException("Only Deadlines and Events can be rescheduled.");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
