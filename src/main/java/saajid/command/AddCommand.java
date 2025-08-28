package saajid.command;

import saajid.task.Task;
import saajid.task.TaskList;


import saajid.storage.Storage;

import saajid.ui.Ui;

/**
 * Represents a command to add a {@link Task} to the task list.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task The task to be added.
     */

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list.
     *
     * @param tasks The task list to add to.
     * @param ui    The UI used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task, ui);
    }

    public Task getTask() {
        return this.task;
    }
}
