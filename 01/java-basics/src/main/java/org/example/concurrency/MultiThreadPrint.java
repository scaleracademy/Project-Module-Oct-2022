package org.example.concurrency;

public class MultiThreadPrint {

    public static void main(String[] args) {
        /*
         * print 1-100 3 times in 3 "parallel" threads
         */

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
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
