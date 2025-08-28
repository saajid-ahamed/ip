package saajid.exception;

/**
 * Represents an exception specific to the Saajid chatbot application.
 */
public class SaajidException  extends Exception {

    /**
     * Constructs a SaajidException with the given message.
     *
     * @param message The error message.
     */
    public SaajidException(String message) {
        super(message);
    }
}
