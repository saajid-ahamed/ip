package saajid.command;

import saajid.task.TaskList;

import saajid.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by showing all tasks.
     *
     * @param tasks The task list to list.
     * @param ui The UI used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.listTasks(ui);
    }
}
