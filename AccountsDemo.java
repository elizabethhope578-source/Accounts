/**
 * AccountsDemo – demonstrates the full Q1 implementation.
 * Shows: deposits, withdrawals (including refused ones),
 * interest, overdraft, generateStatement(), and totalWorth().
 */
public class AccountsDemo {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("   First Community Bank – Q1 Demo    ");
        System.out.println("======================================\n");

        // ── Create accounts ───────────────────────────────────────────────
        SavingsAccount savings = new SavingsAccount("SAV-001", 500_000, 5.0);
        CurrentAccount current = new CurrentAccount("CUR-001", 200_000, 300_000);

        // ── Create customer and add accounts (aggregation) ─────────────────
        Customer customer = new Customer("C001", "Nakato Alice");
        customer.addAccount(savings);
        customer.addAccount(current);

        System.out.println("\n--- Initial State ---");
        System.out.println(customer);

        // ── Savings: valid withdrawal ──────────────────────────────────────
        System.out.println("\n--- Savings: withdraw 100,000 ---");
        savings.withdraw(100_000);

        // ── Savings: refused withdrawal (would go negative) ───────────────
        System.out.println("\n--- Savings: withdraw 600,000 (should be refused) ---");
        savings.withdraw(600_000);

        // ── Savings: add interest ─────────────────────────────────────────
        System.out.println("\n--- Savings: add 5% interest ---");
        savings.addInterest();

        // ── Current: withdrawal into overdraft ────────────────────────────
        System.out.println("\n--- Current: withdraw 400,000 (uses overdraft) ---");
        current.withdraw(400_000);

        // ── Current: withdrawal beyond overdraft limit ────────────────────
        System.out.println("\n--- Current: withdraw 200,000 (exceeds overdraft limit) ---");
        current.withdraw(200_000);

        // ── Deposit on current ────────────────────────────────────────────
        System.out.println("\n--- Current: deposit 150,000 ---");
        current.deposit(150_000);

        // ── Generate statements ───────────────────────────────────────────
        System.out.println("\n--- Statements ---");
        System.out.print(savings.generateStatement());
        System.out.print(current.generateStatement());

        // ── Final customer summary ─────────────────────────────────────────
        System.out.println("\n--- Final Customer Summary ---");
        System.out.println(customer);
    }
}
