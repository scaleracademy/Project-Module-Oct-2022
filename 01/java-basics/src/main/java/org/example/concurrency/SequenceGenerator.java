package org.example.concurrency;


public class SequenceGenerator {
    public static void main(String[] args) {
        /*
         * print 1-100 3 times in 3 "parallel" threads
        Th1 - 1
        Th2 - 1
        Th3 - 1
        Th1 - 2
        Th2 - 2
        Th3 - 2
        Th1 - 3
        Th2 - 3
        Th3 - 3
         */
        ValuePrinter vp = new ValuePrinter(3);
        Thread th1 = new SeqGenerator(0, vp);
        Thread th2 = new SeqGenerator(1, vp);
        Thread th3 = new SeqGenerator(2, vp);

        th1.start();
        th2.start();
        th3.start();

    }
}
