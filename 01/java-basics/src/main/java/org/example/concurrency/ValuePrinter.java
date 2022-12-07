package org.example.concurrency;

public class ValuePrinter {
    int totalThreads;
    int curThread = 0;


    public ValuePrinter(int numTh) {
        totalThreads = numTh;
    }


    public synchronized void print(int thId, int val) throws InterruptedException {
        while (curThread != thId) {
            wait();
        }

        System.out.println("Thread " + thId + " prints " + val);

        curThread = (curThread + 1) % totalThreads;
        notifyAll();
    }
}
