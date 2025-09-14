package saajid.ui;
/*
    AI-assisted: Javadoc comments
    AI provided me with the initial content to include in the javadoc comments for the following refactored methods.
    AI provided a draft of these comments as well. Comments were then refined and implemented.
     */

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

    /**
     * Retrieves all captured messages as a single string.
     *
     * @return all captured messages, trimmed of trailing whitespace
     */
    public String getOutput() {
        return output.toString().trim();
    }
}
