package saajid.command;

import saajid.exception.SaajidException;
import saajid.storage.Storage;
import saajid.task.TaskList;
import saajid.ui.Ui;




public abstract class Command {

    //an abstract class to execute various commands based on the type of command

    public abstract void execute(TaskList tasks, Ui ui) throws SaajidException;

    public boolean isExit() {
        return false;
    }
}

