public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    // check if number entered is available
    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        if (index < 0 || index >= tasks.size()) {
            throw new SaajidException("Task number " + (index + 1) + " does not exist.");
        }
        Task t = tasks.getTask(index);
        t.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " + t); //print message accordingly
    }
}
