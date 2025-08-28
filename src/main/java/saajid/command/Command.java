package saajid.command;

import saajid.task.TaskList;
import saajid.ui.Ui;
import saajid.storage.Storage;
import saajid.exception.SaajidException;


/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes this command on the given task list.
     *
     * @param tasks The task list to modify.
     * @param ui    The UI used for displaying messages.
     * @throws SaajidException If execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws SaajidException;

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return true if the command exits the program, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
