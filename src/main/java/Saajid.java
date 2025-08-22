import java.util.ArrayList;
import java.util.Scanner;

public class Saajid {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;

    public Saajid() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.nextCommand();
            try {
                Command command = parser.parse(input);
                command.execute(tasks, ui);
                isExit = command.isExit();
            } catch (SaajidException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Saajid().run();
    }
}
