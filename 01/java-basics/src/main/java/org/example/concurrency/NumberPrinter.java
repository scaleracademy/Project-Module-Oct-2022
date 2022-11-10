package org.example.concurrency;

/**
 * Print Number 1-MAX by N threads like:
 *  Thread-0: 1
 *  Thread-1: 1
 *  Thread-2: 1
 *  ...
 *  Thread-(n-1): 1
 *
 *  Thread-0: 2
 *  Thread-1: 2
 *  Thread-2: 2
 *  ...
 *  Thread-(n-1): 2
 *
 *  ...
 *  ...
 *
 *  Thread-0: MAX
 *  Thread-1: MAX
 *  Thread-2: MAX
 *  ...
 *  Thread-(n-1): MAX
 */

public class NumberPrinter implements Runnable {

    @Override
    public void run() {


        synchronized () {
            while(counter % totalThreads != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
