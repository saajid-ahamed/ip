package saajid.command;

import saajid.task.TaskList;

import saajid.ui.Ui;


public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.listTasks(ui);
    }
        // listTask function will handle message printing
}
