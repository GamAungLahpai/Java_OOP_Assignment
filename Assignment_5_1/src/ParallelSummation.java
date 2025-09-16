import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;


public class ParallelSummation {
    private static final int ARRAY_SIZE = 100000;
    private static final AtomicLong totalSum = new AtomicLong(0);

    public static void main(String[] args) {
        System.out.println("=== Parallel Number Summation ===");

        // Generate array of random numbers
        int[] numbers = generateRandomArray(ARRAY_SIZE);
        System.out.println("Generated array with " + ARRAY_SIZE + " random integers");

        // Get number of available processor cores
        int numCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processor cores: " + numCores);

        // Calculate chunk size for each thread
        int chunkSize = ARRAY_SIZE / numCores;
        int remainder = ARRAY_SIZE % numCores;

        System.out.println("Chunk size per thread: " + chunkSize);
        System.out.println("Remainder elements: " + remainder);

        // Create threads for parallel processing
        List<Thread> threads = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        int startIndex = 0;
        for (int i = 0; i < numCores; i++) {
            int endIndex = startIndex + chunkSize;

            // Give remainder elements to the last thread
            if (i == numCores - 1) {
                endIndex += remainder;
            }

            Thread worker = new Thread(new SumWorker(numbers, startIndex, endIndex, i + 1));
            threads.add(worker);
            worker.start();

            startIndex = endIndex;
        }

        // Wait for all threads to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();

        // Display results
        System.out.println("\n=== Results ===");
        System.out.println("Total sum (parallel): " + totalSum.get());
        System.out.println("Time taken (parallel): " + (endTime - startTime) + " ms");

        // Verify with sequential calculation
        verifyWithSequentialSum(numbers, startTime);
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducible results
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000); // Random numbers 0-999
        }

        return array;
    }

    private static void verifyWithSequentialSum(int[] numbers, long parallelStartTime) {
        System.out.println("\n=== Verification (Sequential) ===");

        long startTime = System.currentTimeMillis();
        long sequentialSum = 0;

        for (int number : numbers) {
            sequentialSum += number;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Total sum (sequential): " + sequentialSum);
        System.out.println("Time taken (sequential): " + (endTime - startTime) + " ms");

        // Compare results
        boolean resultsMatch = (sequentialSum == totalSum.get());
        System.out.println("Results match: " + resultsMatch);

        if (resultsMatch) {
            System.out.println("✓ Parallel calculation successful!");
        } else {
            System.out.println("✗ Error: Results don't match!");
        }
    }

    static class SumWorker implements Runnable {
        private final int[] numbers;
        private final int startIndex;
        private final int endIndex;
        private final int threadId;

        public SumWorker(int[] numbers, int startIndex, int endIndex, int threadId) {
            this.numbers = numbers;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            long partialSum = 0;
            int elementsProcessed = endIndex - startIndex;

            System.out.println("Thread " + threadId + " started: processing elements " +
                    startIndex + " to " + (endIndex - 1) + " (" + elementsProcessed + " elements)");

            // Calculate sum for assigned portion
            for (int i = startIndex; i < endIndex; i++) {
                partialSum += numbers[i];
            }

            // Add partial sum to total (thread-safe)
            totalSum.addAndGet(partialSum);

            System.out.println("Thread " + threadId + " completed: partial sum = " + partialSum);
        }
    }
}