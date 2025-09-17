package saajid.exception;

/**
 * Represents an exception specific to the Saajid chatbot application.
 */
public class InvalidCommandException  extends Exception {

    /**
     * Constructs a InvalidCommandException with the given message.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
