package org.example.concurrency.multiThreadPrint.roundRobinPrint.waitNotifyApproach;

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@AllArgsConstructor
public class NumberPrinter {

    private int count = 1;
    private int counter = 1;
    private int noOfThreads;
    private int maxNumber;

    public NumberPrinter(int noOfThreads, int maxNumber) {
        this.noOfThreads = noOfThreads;
        this.maxNumber = maxNumber;
    }

    public void printNumbers(int remainder) {
        while(counter < maxNumber*3-1) {
            synchronized (this) {
                while(counter % noOfThreads != remainder) {
                    try {
                        wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " " + count);
                notifyAll();
                if(counter % noOfThreads == 0) {
                    count++;
                }
                counter++;
            }
        }
    }
}
