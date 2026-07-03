import java.io.*;

/**
 * Q2 – Demonstrates:
 *  (a) finally block behaviour and return value tracing
 *  (b) risky(0) and risky(2) output tracing
 *  (c) InsufficientFundsException (checked) + withdraw()
 *  (d) try-with-resources file reading
 */
public class ExceptionsDemo {

    // ── (a) finally return value demo ─────────────────────────────────────
    /**
     * Demonstrates how finally interacts with a return statement.
     * The finally block ALWAYS runs, even when there is a return in try.
     * However, if finally itself has a return, it overrides the try return.
     */
    static int demonstrateFinally() {
        try {
            System.out.println("try block: about to return 10");
            return 10;             // this return is noted but finally runs first
        } finally {
            System.out.println("finally block: always executes");
            // If we had 'return 99' here it would override the 10.
            // Without it, 10 is returned after finally finishes.
        }
    }

    // ── (b) risky method ──────────────────────────────────────────────────
    /**
     * Demonstrates what prints and what is returned for risky(0) and risky(2).
     *
     * risky(0) → divides by zero → ArithmeticException caught
     *            prints: "risky called", "catch: / by zero"
     *            returns: -1
     *
     * risky(2) → no exception
     *            prints: "risky called", "result = 5"
     *            returns: 5
     */
    static int risky(int x) {
        System.out.println("risky called with x=" + x);
        try {
            int result = 10 / x;        // throws if x == 0
            System.out.println("result = " + result);
            return result;
        } catch (ArithmeticException e) {
            System.out.println("catch: " + e.getMessage());
            return -1;
        } finally {
            System.out.println("finally in risky(" + x + ")");
        }
    }

    // ── (c) BankAccount + withdraw with InsufficientFundsException ────────
    static double balance = 100_000; // UGX 100,000

    /**
     * Withdraws amount from balance.
     * Declares and throws InsufficientFundsException if balance is too low.
     *
     * @param amount the amount to withdraw
     * @throws InsufficientFundsException if balance < amount (checked)
     */
    static void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            double shortfall = amount - balance;
            throw new InsufficientFundsException(
                String.format("Cannot withdraw UGX %.2f — balance is only UGX %.2f. Shortfall: UGX %.2f",
                    amount, balance, shortfall),
                shortfall
            );
        }
        balance -= amount;
        System.out.println(String.format("Withdrew UGX %.2f. Remaining balance: UGX %.2f", amount, balance));
    }

    // ── (d) try-with-resources ────────────────────────────────────────────
    /**
     * BEFORE (leaks file handle if exception thrown before close()):
     *
     *   BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
     *   String line = reader.readLine();   // if this throws, close() never runs
     *   System.out.println(line);
     *   reader.close();
     *
     * AFTER (try-with-resources always closes the reader):
     * The resource declared in try(...) is closed automatically
     * in all exit paths — normal, exception, or return.
     */
    static void readFileWithResources(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        // reader is guaranteed closed here — no finally needed
    }

    // ── main ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        // ── (a) finally demo ──────────────────────────────────────────────
        System.out.println("=== (a) finally block demo ===");
        int value = demonstrateFinally();
        System.out.println("Caller received: " + value);
        // Output:
        //   try block: about to return 10
        //   finally block: always executes
        //   Caller received: 10
        // Explanation: finally runs BEFORE the return value is handed to the caller,
        // but since finally doesn't have its own return, the original value 10 is returned.

        // ── (b) risky calls ───────────────────────────────────────────────
        System.out.println("\n=== (b) risky(0) ===");
        int r0 = risky(0);
        System.out.println("risky(0) returned: " + r0);

        System.out.println("\n=== (b) risky(2) ===");
        int r2 = risky(2);
        System.out.println("risky(2) returned: " + r2);

        // ── (c) InsufficientFundsException ───────────────────────────────
        System.out.println("\n=== (c) withdraw demo ===");
        System.out.println("Balance: UGX " + balance);

        try {
            withdraw(40_000);   // succeeds
            withdraw(90_000);   // fails – balance is now 60,000
        } catch (InsufficientFundsException e) {
            System.out.println("CAUGHT InsufficientFundsException: " + e.getMessage());
            System.out.println(String.format("Shortfall was: UGX %.2f", e.getShortfall()));
        }

        // ── (d) try-with-resources ────────────────────────────────────────
        System.out.println("\n=== (d) try-with-resources ===");
        // Write a temp file to demonstrate
        try (PrintWriter pw = new PrintWriter(new FileWriter("temp.txt"))) {
            pw.println("First Bank Uganda");
            pw.println("Transaction log – Q2 demo");
        } catch (IOException e) {
            System.out.println("Error writing temp file: " + e.getMessage());
        }
        readFileWithResources("temp.txt");
        readFileWithResources("missing.txt"); // demonstrates FileNotFoundException
    }
}
