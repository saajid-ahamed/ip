package saajid.command;

import saajid.exception.InvalidCommandException;
import saajid.task.TaskList;
import saajid.ui.Ui;

/*
    AI-assisted: Javadoc comments
    AI provided me with the initial content to include in the javadoc comments for the following refactored methods.
    AI provided a draft of these comments as well. Comments were then refined and implemented.
    */
/**
 * Represents a command that finds tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword
     * and displaying them using the provided UI.
     *
     * @param tasks the TaskList to search in
     * @param ui the UI to display the search results
     * @throws InvalidCommandException if an error occurs while finding tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidCommandException {
        tasks.findTasks(keyword, ui);
    }
}
