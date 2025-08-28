package saajid.command;

import saajid.exception.SaajidException;
import saajid.storage.Storage;
import saajid.task.TaskList;
import saajid.ui.Ui;


/**
 * Represents a command to delete a task from the task list.
 */

public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param index The 0-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing a task from the task list.
     *
     * @param tasks The task list to modify.
     * @param ui The UI used for displaying messages.
     * @throws SaajidException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        tasks.deleteTask(index, ui);
    }

    public int getIndex() {
        return this.index;
    }
}
