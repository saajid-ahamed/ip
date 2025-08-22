public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        tasks.deleteTask(index, ui);
    }
}
