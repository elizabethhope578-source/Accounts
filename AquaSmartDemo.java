/**
 * AquaSmartDemo – demonstrates the SmartMeter class.
 */
public class AquaSmartDemo {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("   AquaSmart – KIS Smart Metering Demo     ");
        System.out.println("============================================\n");

        SmartMeter meter = new SmartMeter("KIS-001", 1000); // UGX 1,000 opening credit
        System.out.println("Initial state: " + meter);

        // Normal consumption
        System.out.println("\n--- Normal consumption (5 litres = UGX 250) ---");
        meter.recordConsumption(5);
        System.out.println(meter);

        // More consumption
        System.out.println("\n--- Consumption (10 litres = UGX 500) ---");
        meter.recordConsumption(10);
        System.out.println(meter);

        // Consumption that exhausts credit (remaining = 250, need 300)
        System.out.println("\n--- Consumption beyond credit (6 litres = UGX 300, only 250 left) ---");
        meter.recordConsumption(6);
        System.out.println(meter);

        // Try dispensing with closed valve
        System.out.println("\n--- Attempt dispensing with closed valve ---");
        meter.recordConsumption(1);

        // Load a token to reopen
        System.out.println("\n--- Loading token: UGX 2,000 ---");
        meter.loadToken(2000);
        System.out.println(meter);

        // Normal consumption after reload
        System.out.println("\n--- Consumption after reload (3 litres = UGX 150) ---");
        meter.recordConsumption(3);
        System.out.println(meter);

        System.out.println("\n============================================");
        System.out.println(" Encapsulation note:");
        System.out.println(" creditBalance is private — external code cannot");
        System.out.println(" tamper with it directly. All changes go through");
        System.out.println(" loadToken() and recordConsumption(), ensuring");
        System.out.println(" billing integrity at KIS.");
        System.out.println("============================================");
    }
}
