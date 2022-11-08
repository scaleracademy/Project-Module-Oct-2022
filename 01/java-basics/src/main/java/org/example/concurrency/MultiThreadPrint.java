package org.example.concurrency;

public class MultiThreadPrint {

    public static void main(String[] args)
    {

        PrintThreadsSequence pts1=new PrintThreadsSequence(1);
        PrintThreadsSequence pts2=new PrintThreadsSequence(2);
        PrintThreadsSequence pts3=new PrintThreadsSequence(0);

        Thread t1=new Thread(pts1,"T1");
        Thread t2=new Thread(pts2,"T2");
        Thread t3=new Thread(pts3,"T3");

        t1.start();
        t2.start();
        t3.start();
    }

}
