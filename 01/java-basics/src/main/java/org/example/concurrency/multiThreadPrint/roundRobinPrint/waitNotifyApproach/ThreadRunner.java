package org.example.concurrency.multiThreadPrint.roundRobinPrint.waitNotifyApproach;

public class ThreadRunner {
    private final int noOfThreads = 3;
    private final int maxNumber = 100;

    public static void main(String[] args) {
        ThreadRunner tr = new ThreadRunner();
        tr.runThreads();
    }
    public void runThreads() {
        NumberPrinter numberPrinter = new NumberPrinter(noOfThreads, maxNumber);

        NumberPrinterRunner np1 = new NumberPrinterRunner(numberPrinter, 1);
        NumberPrinterRunner np2 = new NumberPrinterRunner(numberPrinter, 2);
        NumberPrinterRunner np3 = new NumberPrinterRunner(numberPrinter, 0);

        Thread t1 = new Thread(np1);
        Thread t2 = new Thread(np2);
        Thread t3 = new Thread(np3);

        t1.start();
        t2.start();
        t3.start();
    }
}
