// Task 2: Thread-Safe ArrayList
package Task_2;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class ThreadSafeList<T> {
    private final ArrayList<T> list;

    public ThreadSafeList() {
        this.list = new ArrayList<>();
    }

    public synchronized void add(T element) {
        list.add(element);
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized boolean remove(T element) {
        return list.remove(element);
    }
}

public class ThreadSafetyTester {

    public static void testThreadSafety() {
        ThreadSafeList<String> safeList = new ThreadSafeList<>();
        int numThreads = 5;
        int operationsPerThread = 100;
        CountDownLatch latch = new CountDownLatch(numThreads);

        System.out.println("Testing thread-safety with " + numThreads + " threads...");
        System.out.println("Each thread will add " + operationsPerThread + " elements");

        // Create threads that add elements
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    safeList.add("Thread-" + threadId + "-Item-" + j);
                }
                System.out.println("Thread " + threadId + " completed adding elements");
                latch.countDown();
            }).start();
        }

        try {
            latch.await(); // Wait for all threads to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify results
        int expectedSize = numThreads * operationsPerThread;
        int actualSize = safeList.size();

        System.out.println("\n--- Test Results ---");
        System.out.println("Expected size: " + expectedSize);
        System.out.println("Actual size: " + actualSize);
        System.out.println("Test " + (expectedSize == actualSize ? "PASSED" : "FAILED"));

        // Additional test: Remove elements
        testRemove(safeList);
    }

    public static void testRemove(ThreadSafeList<String> safeList) {
        System.out.println("\n--- Testing Remove Operation ---");
        int sizeBefore = safeList.size();

        // Try to remove some elements
        boolean removed1 = safeList.remove("Thread-0-Item-0");
        boolean removed2 = safeList.remove("Thread-1-Item-1");
        boolean removed3 = safeList.remove("NonExistentItem");

        int sizeAfter = safeList.size();

        System.out.println("Size before removal: " + sizeBefore);
        System.out.println("Removed 'Thread-0-Item-0': " + removed1);
        System.out.println("Removed 'Thread-1-Item-1': " + removed2);
        System.out.println("Removed 'NonExistentItem': " + removed3);
        System.out.println("Size after removal: " + sizeAfter);

        int expectedReductions = (removed1 ? 1 : 0) + (removed2 ? 1 : 0);
        boolean removeTestPassed = (sizeBefore - sizeAfter) == expectedReductions;
        System.out.println("Remove test " + (removeTestPassed ? "PASSED" : "FAILED"));
    }

    public static void main(String[] args) {
        testThreadSafety();
    }
}