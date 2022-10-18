package org.example.concurrency.multiThreadPrint.roundRobinPrint.sleepApproach;

public class PrintNumber implements Runnable {
    @Override
    public void run() {
        for(int i = 1; i <= 100; i++) {
            try{
                Thread.sleep(100);
            } catch(Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
