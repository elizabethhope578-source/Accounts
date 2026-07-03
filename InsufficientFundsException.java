/**
 * InsufficientFundsException – a CHECKED custom exception for the payment system.
 *
 * Design decision: CHECKED (extends Exception, not RuntimeException).
 * Reason: An insufficient balance is a foreseeable, recoverable business condition
 * — the calling code should explicitly handle it (e.g. ask the customer to top up).
 * Checked exceptions force the caller to acknowledge the problem at compile time.
 *
 * UNCHECKED (RuntimeException) would be used for programming errors the caller
 * cannot reasonably recover from (e.g. NullPointerException, ArrayIndexOutOfBoundsException).
 */
public class InsufficientFundsException extends Exception {

    private double shortfall; // how much extra was needed

    /**
     * Creates the exception with a message and the shortfall amount.
     *
     * @param message   description of the problem
     * @param shortfall the amount by which the balance fell short
     */
    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    /** @return the amount by which the balance was insufficient */
    public double getShortfall() { return shortfall; }
}
