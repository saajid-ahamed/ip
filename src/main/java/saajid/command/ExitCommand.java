package saajid.command;

import saajid.task.TaskList;
import saajid.ui.Ui;


public class ExitCommand extends Command {

    //saajid.ui.Ui exit function will handle printing of message
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
