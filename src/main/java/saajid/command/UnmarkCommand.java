package saajid.command;

import saajid.exception.InvalidCommandException;
import saajid.task.Task;
import saajid.task.TaskList;

import saajid.storage.Storage;

import saajid.ui.Ui;


/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param index The 0-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param tasks The task list.
     * @param ui The UI used for displaying messages.
     * @throws InvalidCommandException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidCommandException("Task number " + (index + 1) + " does not exist.");
        }
        Task t = tasks.getTask(index);
        t.markAsNotDone();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + t);
    }

    public int getIndex() {
        return this.index;
    }

}
