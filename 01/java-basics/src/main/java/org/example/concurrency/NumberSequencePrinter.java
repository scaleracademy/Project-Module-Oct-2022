package org.example.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Print Number 1-MAX by N threads like:
 *  Thread-0: 1
 *  Thread-1: 1
 *  Thread-2: 1
 *
 *  Thread-0: 2
 *  Thread-1: 2
 *  Thread-2: 2
 *  ...
 *
 *  Thread-0: MAX
 *  Thread-1: MAX
 *  Thread-2: MAX
 */
public class NumberSequencePrinter implements Runnable {
    private int maxNumber;
    private int threadCount;
    private AtomicInteger turn;
    private int my_id;

    public NumberSequencePrinter(int maxNumber, int threadCount, AtomicInteger turn, int id) {
        this.maxNumber = maxNumber;
        this.threadCount = threadCount;
        this.turn = turn;
        this.my_id = id;
    }

    @Override
    public void run() {
        int i = 1;

        while (i <= this.maxNumber) {
            synchronized (turn) {
                // Wait for my turn.
                while (turn.get() % threadCount != my_id) {
                    try {
                        turn.wait();
                    } catch (InterruptedException ex) {
                    }
                }

                System.out.println(Thread.currentThread().getName() + ": " + i);
                ++i;
                if (my_id == threadCount - 1)
                    System.out.println();

                turn.incrementAndGet();
                turn.notifyAll();
            }
        }
    }
}
