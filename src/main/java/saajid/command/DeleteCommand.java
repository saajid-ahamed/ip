package saajid.command;

import saajid.exception.SaajidException;

import saajid.storage.Storage;

import saajid.task.TaskList;

import saajid.ui.Ui;




public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        tasks.deleteTask(index, ui);
    }

    public int getIndex() {
        return this.index;
    }

    //deleteTask will handle the message printing
}
