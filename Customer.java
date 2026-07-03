import java.util.ArrayList;
import java.util.List;

/**
 * Customer – aggregates zero or more Account objects.
 *
 * Relationship: Customer ◇──> Account  (Aggregation, 0..*)
 *   A customer can hold many accounts; accounts can exist independently.
 *
 * totalWorth() returns the sum of balances across all accounts.
 */
public class Customer {

    // ── Fields ────────────────────────────────────────────────────────────
    private String       customerId;
    private String       name;
    private List<Account> accounts;   // aggregation – 0..* accounts

    // ── Constructor ───────────────────────────────────────────────────────
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name       = name;
        this.accounts   = new ArrayList<>();
    }

    // ── Account management ────────────────────────────────────────────────

    /**
     * Adds an account to this customer's portfolio.
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account " + account.getAccountNumber()
                + " added to customer " + name + ".");
    }

    /**
     * Returns the total worth of this customer –
     * the sum of balances across all their accounts.
     *
     * @return total balance in UGX
     */
    public double totalWorth() {
        double total = 0;
        for (Account a : accounts) {
            total += a.getBalance();
        }
        return total;
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public String        getCustomerId() { return customerId; }
    public String        getName()       { return name; }
    public List<Account> getAccounts()   { return accounts; }

    // ── toString ──────────────────────────────────────────────────────────
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Customer[ID=%s, Name=%s]%n", customerId, name));
        for (Account a : accounts) {
            sb.append("  ").append(a).append("\n");
        }
        sb.append(String.format("  Total Worth: UGX %.2f", totalWorth()));
        return sb.toString();
    }
}
