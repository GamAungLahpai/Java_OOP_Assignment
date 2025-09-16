public class AlternatingNumberPrinter {
    private static final Object lock = new Object();
    private static boolean oddTurn = true;

    public static void main(String[] args) {
        int maxNumber = 20;

        Thread oddThread = new Thread(new OddPrinter(maxNumber));
        Thread evenThread = new Thread(new EvenPrinter(maxNumber));

        oddThread.start();
        evenThread.start();

        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing complete.");
    }

    static class OddPrinter implements Runnable {
        private final int maxNumber;

        public OddPrinter(int maxNumber) {
            this.maxNumber = maxNumber;
        }

        @Override
        public void run() {
            for (int i = 1; i <= maxNumber; i += 2) {
                synchronized (lock) {
                    while (!oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    System.out.println("Odd Thread: " + i);
                    oddTurn = false;
                    lock.notifyAll();
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        private final int maxNumber;

        public EvenPrinter(int maxNumber) {
            this.maxNumber = maxNumber;
        }

        @Override
        public void run() {
            for (int i = 2; i <= maxNumber; i += 2) {
                synchronized (lock) {
                    while (oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    System.out.println("Even Thread: " + i);
                    oddTurn = true;
                    lock.notifyAll();
                }
            }
        }
    }
}