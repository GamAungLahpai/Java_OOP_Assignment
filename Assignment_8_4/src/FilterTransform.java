import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FilterTransform {
    public static void main(String[] args) {
        // Given list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("=== Task 2: Filter, Transform, and Sum ===\n");
        System.out.println("Original List: " + numbers);

        // Complete solution in one stream pipeline
        System.out.println("\n--- Complete Solution ---");
        int result = numbers.stream()
                .filter(n -> n % 2 != 0)      // Filter out even numbers (keep odd)
                .map(n -> n * 2)              // Double each number
                .mapToInt(Integer::intValue)  // Convert to IntStream for sum
                .sum();                       // Find the sum

        System.out.println("Result: " + result);

        // Step-by-step breakdown with intermediate results
        System.out.println("\n--- Step-by-Step Breakdown ---");

        // Step 1: Filter out even numbers
        List<Integer> oddNumbers = numbers.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        System.out.println("Step 1 - After filtering (odd numbers only): " + oddNumbers);

        // Step 2: Double each remaining number
        List<Integer> doubledNumbers = oddNumbers.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println("Step 2 - After doubling: " + doubledNumbers);

        // Step 3: Find the sum
        int sum = doubledNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Step 3 - Sum of doubled odd numbers: " + sum);

        // Using functional interfaces explicitly
        System.out.println("\n--- Using Functional Interfaces ---");

        // Define functional interfaces
        Predicate<Integer> isOdd = n -> n % 2 != 0;
        Function<Integer, Integer> doubleValue = n -> n * 2;

        int resultWithInterfaces = numbers.stream()
                .filter(isOdd)
                .map(doubleValue)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Result using functional interfaces: " + resultWithInterfaces);

        // Alternative approaches
        System.out.println("\n--- Alternative Approaches ---");

        // Using reduce instead of sum
        int resultWithReduce = numbers.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .reduce(0, Integer::sum);
        System.out.println("Result using reduce: " + resultWithReduce);

        // Using method references
        Predicate<Integer> isEven = n -> n % 2 == 0;
        int resultMethodRef = numbers.stream()
                .filter(isEven.negate())  // Filter out even (keep odd)
                .map(n -> n * 2)
                .reduce(0, Integer::sum);
        System.out.println("Result using method references: " + resultMethodRef);

        // Testing with different lists
        System.out.println("\n--- Testing with Different Lists ---");

        List<Integer> test1 = Arrays.asList(2, 4, 6, 8, 10);  // All even
        int result1 = test1.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("All even numbers " + test1 + " -> Result: " + result1);

        List<Integer> test2 = Arrays.asList(1, 3, 5, 7, 9);  // All odd
        int result2 = test2.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("All odd numbers " + test2 + " -> Result: " + result2);

        List<Integer> test3 = Arrays.asList(15, 22, 33, 44, 55);  // Mixed
        int result3 = test3.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * 2)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Mixed numbers " + test3 + " -> Result: " + result3);

        // Show the calculation for clarity
        System.out.println("\n--- Calculation Explanation ---");
        System.out.println("Original: " + numbers);
        System.out.println("Odd numbers: 1, 3, 5, 7, 9");
        System.out.println("After doubling: 2, 6, 10, 14, 18");
        System.out.println("Sum: 2 + 6 + 10 + 14 + 18 = 50");
    }
}