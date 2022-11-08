package org.example.concurrency;

public class MultiThreadPrint {

    public static void main(String[] args) {
        /*
         * print 1-100 3 times in 3 "parallel" threads
         */

        int max = 100;
        int threadCount = 3;
        AtomicInteger turn = new AtomicInteger(0);

        Thread t1 = new Thread(new NumberSequencePrinter(max, threadCount, turn, 0));
        Thread t2 = new Thread(new NumberSequencePrinter(max, threadCount, turn, 1));
        Thread t3 = new Thread(new NumberSequencePrinter(max, threadCount, turn, 2));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException ex) {
        }
    }
}
