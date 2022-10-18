package org.example.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPrinter implements Runnable {
    private int MAX_COUNT;

    private AtomicInteger counter;

    private int threadIndex;

    private int ttlThreads;
    public ThreadPrinter(AtomicInteger counter, int max_count, int threadIndex, int ttlThreads) {
        this.counter = counter;
        this.MAX_COUNT = max_count;
        this.threadIndex = threadIndex;
        this.ttlThreads = ttlThreads;
    }

    @Override
    public void run() {
        while (counter.get() < MAX_COUNT*ttlThreads) {
            if (counter.get() % ttlThreads == threadIndex && counter.get() < MAX_COUNT*ttlThreads) {
                System.out.println(Thread.currentThread().getName() + " - " + (counter.get() / ttlThreads + 1));
                counter.incrementAndGet(); //this synchronizes the run(), as only one thread satisfies the wrapping "if" condition and gets to increment this turn.
            }
        }
    }
}
