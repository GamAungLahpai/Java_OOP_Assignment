import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        // Step 1: Create a list of integers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(8);
        numbers.add(20);
        numbers.add(15);
        numbers.add(3);
        numbers.add(12);

        System.out.println("Original List: " + numbers);

        // Step 2: Filter even numbers (remove them, keeping only odd numbers)
        // First, let's create a copy to show the filtering
        List<Integer> oddNumbers = new ArrayList<>(numbers);
        oddNumbers.removeIf(n -> n % 2 == 0);  // Remove if number is even

        System.out.println("\nAfter Filtering (Only Odd Numbers): " + oddNumbers);

        // Step 3: Double the odd numbers using replaceAll()
        oddNumbers.replaceAll(n -> n * 2);

        System.out.println("After Doubling Odd Numbers: " + oddNumbers);

        // Step 4: Sum all numbers using lambda
        // Using a simple loop with lambda-like approach
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println("\nSum of all numbers in original list: " + sum);

        // Alternative: Using forEach with a wrapper to accumulate
        final int[] sumArray = {0};  // Using array as a wrapper for final variable
        numbers.forEach(n -> sumArray[0] += n);
        System.out.println("Sum using forEach: " + sumArray[0]);

        // --- Additional Examples ---
        System.out.println("\n--- Additional Examples ---");

        // Example 1: Filter and keep even numbers instead
        List<Integer> evenNumbers = new ArrayList<>(numbers);
        evenNumbers.removeIf(n -> n % 2 != 0);  // Remove if number is odd
        System.out.println("\nEven Numbers Only: " + evenNumbers);

        // Example 2: Triple all numbers
        List<Integer> tripled = new ArrayList<>(numbers);
        tripled.replaceAll(n -> n * 3);
        System.out.println("All Numbers Tripled: " + tripled);

        // Example 3: Square all numbers
        List<Integer> squared = new ArrayList<>(numbers);
        squared.replaceAll(n -> n * n);
        System.out.println("All Numbers Squared: " + squared);

        // Example 4: Filter numbers greater than 10
        List<Integer> greaterThanTen = new ArrayList<>(numbers);
        greaterThanTen.removeIf(n -> n <= 10);
        System.out.println("Numbers Greater Than 10: " + greaterThanTen);

        // Example 5: Calculate product of all numbers
        final int[] product = {1};
        numbers.forEach(n -> product[0] *= n);
        System.out.println("\nProduct of all numbers: " + product[0]);

        // Example 6: Count how many numbers are even
        final int[] evenCount = {0};
        numbers.forEach(n -> {
            if (n % 2 == 0) {
                evenCount[0]++;
            }
        });
        System.out.println("Count of even numbers: " + evenCount[0]);

        // Example 7: Find maximum value
        int max = numbers.get(0);
        for (Integer n : numbers) {
            if (n > max) {
                max = n;
            }
        }
        System.out.println("Maximum value: " + max);

        // Example 8: Find minimum value
        int min = numbers.get(0);
        for (Integer n : numbers) {
            if (n < min) {
                min = n;
            }
        }
        System.out.println("Minimum value: " + min);
    }
}