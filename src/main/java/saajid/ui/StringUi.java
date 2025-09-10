package saajid.ui;
/**
 * A Ui variant that captures messages into a String
 * instead of printing them, for GUI integration.
 */
public class StringUi extends Ui {

    private final StringBuilder output = new StringBuilder();

    @Override
    public void showMessage(String message) {
        output.append(message).append("\n");
    }

    @Override
    public void showError(String message) {
        output.append(message).append("\n");
    }

    public String getOutput() {
        return output.toString().trim();
    }
}
