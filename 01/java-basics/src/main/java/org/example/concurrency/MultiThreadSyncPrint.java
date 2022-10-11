package org.example.concurrency;

public class MultiThreadSyncPrint {
        public static void main(String[] args) {

            PrintSequenceRunnable runnable1=new PrintSequenceRunnable(1);
            PrintSequenceRunnable runnable2=new PrintSequenceRunnable(2);
            PrintSequenceRunnable runnable3=new PrintSequenceRunnable(0);

            Thread t1=new Thread(runnable1,"T1");
            Thread t2=new Thread(runnable2,"T2");
            Thread t3=new Thread(runnable3,"T3");

            t1.start();
            t2.start();
            t3.start();
        }

}
