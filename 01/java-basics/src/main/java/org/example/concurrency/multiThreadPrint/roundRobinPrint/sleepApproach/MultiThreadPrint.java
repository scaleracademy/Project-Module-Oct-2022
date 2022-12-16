package org.example.concurrency.multiThreadPrint.roundRobinPrint.sleepApproach;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadPrint {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        PrintNumber pn = new PrintNumber();
        executorService.execute(pn);
        executorService.execute(pn);
        executorService.execute(pn);

        //        shutdown
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(15000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
                System.out.println("shutdown");
            }
        } catch(Exception e) {
            executorService.shutdownNow();
            System.out.println("shutdown");
        }
    }
}
