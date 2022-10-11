package org.example.concurrency;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadPrint {

    public static void main(String[] args) {
        /*
         * print 1-100 3 times in 3 "parallel" threads
         */

        final Integer nThreads = 3;
        final Integer startValue = 1;
        final Integer finalValue = 100;

        State state = new State(nThreads);
        List<Thread> printers = new ArrayList<>();

        for (int i = 0; i < nThreads; i++) {
            printers.add(new Thread(new Printer(state, startValue, finalValue, i)));
            printers.get(i).start();
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
