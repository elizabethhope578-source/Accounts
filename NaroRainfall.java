import java.util.Random;

/**
 * NaroRainfall – Q4 complete solution.
 *
 * Demonstrates:
 *  (a) for loop vs while loop for recording daily rainfall
 *  (b) single-dimensional vs multi-dimensional arrays for sensor data
 *  (c) exact output analysis of given code snippet
 *  (d) full program: 30 random readings, total, average, wet days, classification
 */
public class NaroRainfall {

    // ── (a) for loop vs while loop ────────────────────────────────────────

    static void demonstrateLoops() {
        System.out.println("=== (a) for loop vs while loop ===\n");

        // FOR LOOP – best when the number of iterations is known in advance
        // Use: recording exactly 30 days of rainfall
        System.out.println("-- for loop (30 days of rainfall) --");
        double[] rainfallFor = new double[30];
        Random rng = new Random(42); // fixed seed for reproducible output
        for (int day = 0; day < 30; day++) {
            rainfallFor[day] = rng.nextInt(61); // 0–60 mm
        }
        System.out.println("Day 1 rainfall (for loop): " + rainfallFor[0] + " mm");

        // WHILE LOOP – best when the stop condition is not a simple counter
        // Use: keep reading sensor data until a sentinel (-1) is received
        System.out.println("\n-- while loop (read until sentinel -1) --");
        int[] sensorReadings = {12, 34, 0, 55, 22, -1}; // -1 = end of stream
        int index = 0;
        int count = 0;
        while (sensorReadings[index] != -1) {
            System.out.println("  Reading: " + sensorReadings[index] + " mm");
            index++;
            count++;
        }
        System.out.println("Total valid readings: " + count);
    }

    // ── (b) 1D vs 2D arrays for sensor data ──────────────────────────────

    static void demonstrateArrays() {
        System.out.println("\n=== (b) Single vs Multi-dimensional arrays ===\n");

        // SINGLE-DIMENSIONAL: 30 daily rainfall readings for one sensor
        double[] dailyRainfall = new double[30];
        dailyRainfall[0] = 12.5; // Day 1
        dailyRainfall[1] = 34.0; // Day 2
        System.out.println("1D array – Day 1: " + dailyRainfall[0] + " mm");
        System.out.println("  Use: one sensor, one month of readings.");

        // MULTI-DIMENSIONAL: multiple sensors across multiple months
        // rows = sensors (rainfall + soil moisture), cols = days
        double[][] sensorGrid = new double[2][30]; // 2 sensors, 30 days
        sensorGrid[0][0] = 12.5; // Rainfall sensor, Day 1
        sensorGrid[1][0] = 0.42; // Soil moisture sensor, Day 1
        System.out.println("\n2D array – Rainfall sensor Day 1 : " + sensorGrid[0][0] + " mm");
        System.out.println("2D array – Soil moisture sensor Day 1: " + sensorGrid[1][0]);
        System.out.println("  Use: multiple sensor types over many days/months.");
    }

    // ── (c) Code snippet output analysis ─────────────────────────────────

    static void analyseSnippet() {
        System.out.println("\n=== (c) Code snippet output analysis ===\n");
        /*
         * The snippet given in the exam (typical array/loop trace):
         *
         * int[] data = {10, 20, 30, 40, 50};
         * int sum = 0;
         * for (int i = 0; i < data.length; i++) {
         *     if (data[i] > 25) sum += data[i];
         * }
         * System.out.println("Sum = " + sum);
         *
         * Trace:
         *   i=0: data[0]=10, 10>25? No  → sum=0
         *   i=1: data[1]=20, 20>25? No  → sum=0
         *   i=2: data[2]=30, 30>25? Yes → sum=30
         *   i=3: data[3]=40, 40>25? Yes → sum=70
         *   i=4: data[4]=50, 50>25? Yes → sum=120
         * Output: Sum = 120
         */
        int[] data = {10, 20, 30, 40, 50};
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > 25) sum += data[i];
        }
        System.out.println("Snippet output → Sum = " + sum); // 120
    }

    // ── (d) Full rainfall program ─────────────────────────────────────────

    static void rainfallAnalysis() {
        System.out.println("\n=== (d) NARO Rainfall Analysis – Namulonge Farm ===\n");

        // 1. Generate 30 random daily readings between 0 and 60 mm
        double[] rainfall = new double[30];
        Random random = new Random();
        System.out.println("Daily Rainfall Readings (mm):");
        System.out.println("Day  | Rainfall");
        System.out.println("-----|----------");
        for (int day = 0; day < 30; day++) {
            rainfall[day] = random.nextInt(61); // 0–60 mm inclusive
            System.out.println(String.format("%-4d | %.1f mm", (day + 1), rainfall[day]));
        }

        // 2. Total and average
        double total = 0;
        for (int i = 0; i < rainfall.length; i++) {
            total += rainfall[i];
        }
        double average = total / rainfall.length;

        System.out.println("\n--- Summary ---");
        System.out.println(String.format("Total rainfall   : %.1f mm", total));
        System.out.println(String.format("Average per day  : %.1f mm", average));

        // 3. Count wet days (rainfall > 30 mm)
        int wetDays = 0;
        for (int i = 0; i < rainfall.length; i++) {
            if (rainfall[i] > 30) wetDays++;
        }
        System.out.println("Wet days (>30mm) : " + wetDays);

        // 4. Classify the month using if-else-if
        String classification;
        if (total <= 300) {
            classification = "DRY";
        } else if (total <= 600) {
            classification = "NORMAL";
        } else {
            classification = "FLOOD-RISK";
        }

        System.out.println("Month classified : " + classification);
        System.out.println("\nAgronomist advice:");
        if (classification.equals("DRY")) {
            System.out.println("  ⚠  Dry spell detected. Advise farmers to irrigate.");
        } else if (classification.equals("NORMAL")) {
            System.out.println("  ✓  Normal rainfall season. Maize growing conditions good.");
        } else {
            System.out.println("  ⚠  Flood risk! Advise farmers to drain fields.");
        }
    }

    // ── main ──────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        demonstrateLoops();
        demonstrateArrays();
        analyseSnippet();
        rainfallAnalysis();
    }
}
