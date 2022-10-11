package org.example.concurrency;

/**
 * ASSIGNMENT (PROJECT CLASS 01)
 *
 * Write more ways of achieving the above goal.
 *
 * BONUS:
 *  Find a way to exactly print (i.e. strict round-robin order)
 *
 *  Thread 1 - 1
 *  Thread 2 - 1
 *  Thread 3 - 1
 *  Thread 1 - 2
 *  Thread 2 - 2
 *  Thread 3 - 2
 */
public class MultiThreadPrintInSync {

    private static final Object lock = new Object();
    private static int NO_OF_THREADS = 3;
    private static int PRINT_NUMBERS_UPTO = 10;
    private static int counter = 0 ;

    public static void main(String[] args) {

        // Creating threads
        for (int i = 0; i < NO_OF_THREADS; i++) {
            new Thread(new Generator(i), "T" + (i+1)).start();
        }
    }

    static class Generator implements Runnable {

        private int remainder;

        Generator(int remainder) {
            this.remainder = remainder;
        }

        @Override
        public void run() {
            while (counter <= PRINT_NUMBERS_UPTO*NO_OF_THREADS) {
                synchronized (lock) {
                    while (counter % NO_OF_THREADS != remainder) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (counter % NO_OF_THREADS == remainder &&
                            counter <= PRINT_NUMBERS_UPTO*NO_OF_THREADS) {
                        System.out.println(Thread.currentThread().getName()
                                + "-" + (counter/NO_OF_THREADS+1));
                    }
                    counter++;
                    lock.notifyAll();
                }

            }
        }

    }
}
