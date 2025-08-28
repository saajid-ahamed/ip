package saajid.command;

import saajid.storage.Storage;
import saajid.task.Task;
import saajid.task.TaskList;
import saajid.ui.Ui;


public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    //add task will handle the printing of message
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task, ui);
    }

    public Task getTask() {
        return this.task;
    }
}
