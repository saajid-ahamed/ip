package saajid.command;

import saajid.exception.SaajidException;
import saajid.task.TaskList;
import saajid.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        tasks.findTasks(keyword, ui);
    }
}
