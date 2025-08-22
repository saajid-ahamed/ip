public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui) throws SaajidException;

    public boolean isExit() {
        return false;
    }
}
