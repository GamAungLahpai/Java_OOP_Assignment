import java.util.Arrays;
import java.util.OptionalDouble;

public class FunctionalMean {
    public static void main(String[] args) {
        // Test arrays
        int[] numbers1 = {10, 20, 30, 40, 50};
        int[] numbers2 = {5, 15, 25, 35, 45, 55};
        double[] numbers3 = {2.5, 3.5, 4.5, 5.5};

        System.out.println("=== Task 1: Calculate Mean Functionally ===\n");

        // Method 1: Using average() - Most straightforward
        System.out.println("Array 1: " + Arrays.toString(numbers1));
        double mean1 = Arrays.stream(numbers1)
                .average()
                .orElse(0.0);
        System.out.println("Mean (using average()): " + mean1);

        System.out.println("\nArray 2: " + Arrays.toString(numbers2));
        double mean2 = Arrays.stream(numbers2)
                .average()
                .orElse(0.0);
        System.out.println("Mean (using average()): " + mean2);

        // Method 2: Using sum and count
        System.out.println("\n--- Alternative Method: Using sum/count ---");
        System.out.println("Array 1: " + Arrays.toString(numbers1));
        long count = Arrays.stream(numbers1).count();
        int sum = Arrays.stream(numbers1).sum();
        double mean3 = (double) sum / count;
        System.out.println("Sum: " + sum + ", Count: " + count);
        System.out.println("Mean: " + mean3);

        // Method 3: Using reduce
        System.out.println("\n--- Alternative Method: Using reduce ---");
        System.out.println("Array 2: " + Arrays.toString(numbers2));
        OptionalDouble mean4 = Arrays.stream(numbers2)
                .mapToDouble(i -> i)
                .reduce((a, b) -> a + b)
                .stream()
                .map(total -> total / numbers2.length)
                .findFirst();
        System.out.println("Mean (using reduce): " + mean4.orElse(0.0));

        // Method 4: For double arrays
        System.out.println("\n--- Working with Double Array ---");
        System.out.println("Array 3: " + Arrays.toString(numbers3));
        double mean5 = Arrays.stream(numbers3)
                .average()
                .orElse(0.0);
        System.out.println("Mean: " + mean5);

        // Method 5: Custom functional approach with statistics
        System.out.println("\n--- Using Statistics Collector ---");
        System.out.println("Array 1: " + Arrays.toString(numbers1));
        var stats = Arrays.stream(numbers1)
                .summaryStatistics();
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Mean: " + stats.getAverage());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());

        // Edge case: Empty array
        System.out.println("\n--- Edge Case: Empty Array ---");
        int[] emptyArray = {};
        double meanEmpty = Arrays.stream(emptyArray)
                .average()
                .orElse(0.0);
        System.out.println("Empty array mean: " + meanEmpty);

        // Edge case: Single element
        System.out.println("\n--- Edge Case: Single Element ---");
        int[] singleElement = {42};
        double meanSingle = Arrays.stream(singleElement)
                .average()
                .orElse(0.0);
        System.out.println("Single element array [42] mean: " + meanSingle);
    }
}