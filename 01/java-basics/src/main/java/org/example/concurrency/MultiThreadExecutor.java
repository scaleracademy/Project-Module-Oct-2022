package org.example.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MultiThreadExecutor {
    static int  priority = 0;
    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 100; i++){
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                    try {
                        Thread.sleep(100,100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 3; i++){
            executorService.execute(r);
        }
        executorService.shutdown();
    }
}
