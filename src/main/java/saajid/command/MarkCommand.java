package saajid.command;

import saajid.exception.SaajidException;
import saajid.storage.Storage;
import saajid.task.Task;
import saajid.task.TaskList;
import saajid.ui.Ui;



/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;
    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param index The 0-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as done.
     *
     * @param tasks The task list.
     * @param ui    The UI used for displaying messages.
     * @throws SaajidException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        if (index < 0 || index >= tasks.size()) {
            throw new SaajidException("Task number " + (index + 1) + " does not exist.");
        }
        Task t = tasks.getTask(index);
        t.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + t);
    }

    public int getIndex() {
        return this.index;
    }
}
