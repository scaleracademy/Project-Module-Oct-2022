package org.example.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadPrint {

    public static void main(String[] args) {
        /*
         * print 1-100 3 times in 3 "parallel" threads
         */

        AtomicInteger counter = new AtomicInteger(0);
        int n = 3;
        int max_count = 100;
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new ThreadPrinter(counter, max_count, i, n), "Thread " + (i + 1));
            t.start();
        }
    }

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

}
