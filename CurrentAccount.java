/**
 * CurrentAccount – a concrete subclass of Account.
 *
 * Rule:
 *  - withdraw() may take the balance negative, but only as far as overdraftLimit.
 *    e.g. if overdraftLimit = 500,000 the balance can go as low as -500,000.
 */
public class CurrentAccount extends Account {

    private double overdraftLimit; // maximum amount the balance can go negative

    // ── Constructor ───────────────────────────────────────────────────────
    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    // ── withdraw – overdraft allowed up to limit ──────────────────────────
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        // the lowest balance can go is -overdraftLimit
        if (balance - amount < -overdraftLimit) {
            System.out.printf(
                "Withdrawal of UGX %.2f refused – exceeds overdraft limit of UGX %.2f.%n",
                amount, overdraftLimit);
            return;
        }
        balance -= amount;
        System.out.println(String.format("Withdrew UGX %.2f. New balance: UGX %.2f%n", amount, balance));
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public double getOverdraftLimit() { return overdraftLimit; }

    @Override
    public String toString() {
        return String.format("CurrentAccount[No=%s, Balance=UGX %.2f, Overdraft limit=UGX %.2f]",
                getAccountNumber(), balance, overdraftLimit);
    }
}
