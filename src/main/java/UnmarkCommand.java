public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws SaajidException {
        if (index < 0 || index >= tasks.size()) {   //check if number entered is valid
            throw new SaajidException("Task number " + (index + 1) + " does not exist.");
        }
        Task t = tasks.getTask(index);
        t.markAsNotDone();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + t);
    }

}
