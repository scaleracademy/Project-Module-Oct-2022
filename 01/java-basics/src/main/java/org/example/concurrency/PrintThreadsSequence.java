package org.example.concurrency;

/*
Program to print the Sequence using lock and wait Mechanism
 */
public class PrintThreadsSequence implements Runnable {


    public int PRINT_NUMBERS_UPTO = 100;
    static int number = 1;
    static int counter = 1;

    int remainder;
    static Object lock = new Object();

    PrintThreadsSequence(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run() {
        while (counter < PRINT_NUMBERS_UPTO * 3 - 1) {
            synchronized (lock) {
                while (counter % 3 != remainder) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                System.out.println(Thread.currentThread().getName() + " " + number);
                lock.notifyAll();
                if (counter % 3 == 0) {
                    number++;
                }
                counter++;


            }
        }
    }

}
