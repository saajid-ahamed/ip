public class ExitCommand extends Command {

    //Ui exit function will handle printing of message
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
