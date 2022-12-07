package org.example.concurrency;

public class SeqGenerator extends Thread {
    ValuePrinter vp;
    int threadId;

    public SeqGenerator(int thId, ValuePrinter pp) {
        threadId = thId;
        vp = pp;
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);


            }
        }
    };
    @Override
    public void run() {
        for (int i=1;i<=10;i++) {
            try {
                vp.print(threadId, i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

}

