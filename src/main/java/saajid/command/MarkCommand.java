package saajid.command;

import saajid.exception.SaajidException;

import saajid.task.Task;
import saajid.task.TaskList;


import saajid.storage.Storage;

import saajid.ui.Ui;




public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    // check if number entered is available
    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        if (index < 0 || index >= tasks.size()) {
            throw new SaajidException("saajid.task.Task number " + (index + 1) + " does not exist.");
        }
        Task t = tasks.getTask(index);
        t.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + t); //print message accordingly
    }

    public int getIndex() {
        return this.index;
    }
}
