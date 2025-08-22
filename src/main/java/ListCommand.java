public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.listTasks(ui);
    }
}
