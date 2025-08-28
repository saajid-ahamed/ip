package saajid.command;

import saajid.task.TaskList;
import saajid.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying the goodbye message.
     *
     * @param tasks The task list (not used here).
     * @param ui The UI used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }

    /**
     * Indicates that this command will terminate the program.
     *
     * @return true Always returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
