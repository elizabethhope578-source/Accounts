/**
 * Abstract Account class – implements Statement and defines
 * common state shared by all account types.
 *
 * Visibility markers:
 *   - accountNumber : private  (encapsulated)
 *   # balance       : protected (subclasses need direct access)
 *   + deposit()     : public
 *   + withdraw()    : public abstract (each subclass enforces its own rule)
 */
public abstract class Account implements Statement {

    // ── Fields ────────────────────────────────────────────────────────────
    private String accountNumber;   // private – only accessible via getter
    protected double balance;       // protected – subclasses read/write directly

    // ── Constructor ───────────────────────────────────────────────────────
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance       = initialBalance;
    }

    // ── Concrete methods ──────────────────────────────────────────────────

    /**
     * Deposits the given amount into the account.
     *
     * @param amount must be positive
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.println(String.format("Deposited UGX %.2f. New balance: UGX %.2f%n", amount, balance));
    }

    /** @return current balance */
    public double getBalance() { return balance; }

    /** @return account number */
    public String getAccountNumber() { return accountNumber; }

    // ── Abstract method ───────────────────────────────────────────────────

    /**
     * Withdraws the given amount. Each subclass enforces its own rule
     * (e.g. SavingsAccount refuses to go below zero;
     *  CurrentAccount allows overdraft up to a limit).
     *
     * @param amount the amount to withdraw
     */
    public abstract void withdraw(double amount);

    // ── Statement interface ───────────────────────────────────────────────

    @Override
    public String generateStatement() {
        return String.format(
            "=== Account Statement ===%n" +
            "Account No : %s%n" +
            "Balance    : UGX %.2f%n" +
            "=========================%n",
            accountNumber, balance);
    }

    // ── toString ──────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("Account[No=%s, Balance=UGX %.2f]", accountNumber, balance);
    }
}
