import java.util.Scanner;

public class Task_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter the integers into the array:");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        int maxSum = array[0];
        int startIndex = 0;
        int endIndex = 0;

        // Check all possible subarrays
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                int currentSum = 0;

                // Calculate sum of subarray from i to j
                for (int k = i; k <= j; k++) {
                    currentSum += array[k];
                }

                // Update maximum if current sum is greater
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    startIndex = i;
                    endIndex = j;
                }
            }
        }

        System.out.println("Maximum sum: " + maxSum);
        System.out.println("Integers: " + (startIndex + 1) + "-" + (endIndex + 1));

        scanner.close();
    }
}