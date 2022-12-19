package org.example.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadPoolExecutor {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+" : "+i);
//                    try {
//                        Thread.currentThread().sleep(100);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                }
            }
        };

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>( 5), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r,"numberPrinterThread - "+Thread.currentThread().getId());
                return  thread;
            }
        });

        for(int i = 0; i < 3; i++){
            tpe.execute(r);
        }
        tpe.shutdown();
    }
}
