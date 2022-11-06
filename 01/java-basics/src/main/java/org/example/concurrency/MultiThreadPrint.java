package org.example.concurrency;

public class MultiThreadPrint {

    public static void main(String[] args) {
        /*
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
        DataPrinter printer = new DataPrinter();
        Thread th1 = new SeqGenerator(printer);
        Thread th2 = new SeqGenerator(printer);
        Thread th3 = new SeqGenerator(printer);

        th1.start();
        th2.start();
        th3.start();


    }

    public static class SeqGenerator extends  Thread {
        DataPrinter printer;

        int endVal =0;

        public SeqGenerator(int n) {
            endVal = n;
        }

        public SeqGenerator(DataPrinter pp) {
            printer = pp;
            printer.registerThread(this.getName());
        }

        @Override
        public void run() {
            for (int i=1;i<=10;i++) {
                try {
                    printer.print(this.getName(), i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static class DataPrinter  {
        int curThread = 0;

        List<String> threads = new ArrayList<>();
        public void registerThread(String threadId) {
            threads.add(threadId);
            System.out.println(threadId);
        }

        public synchronized void print(String thId, int val) throws InterruptedException {
            while(!threads.get(curThread).equals(thId)) {
                wait();
            }

            System.out.println("Thread " + thId + " prints " + val);

            curThread= (curThread + 1) % threads.size();
            notifyAll();
        }
    }
/**
Output 

Thread 0 prints 1
Thread 1 prints 1
Thread 2 prints 1
Thread 0 prints 2
Thread 1 prints 2
Thread 2 prints 2
Thread 0 prints 3
Thread 1 prints 3
Thread 2 prints 3
Thread 0 prints 4
Thread 1 prints 4
Thread 2 prints 4
Thread 0 prints 5
Thread 1 prints 5
Thread 2 prints 5
Thread 0 prints 6
Thread 1 prints 6
Thread 2 prints 6
Thread 0 prints 7
Thread 1 prints 7
Thread 2 prints 7
Thread 0 prints 8
Thread 1 prints 8
Thread 2 prints 8
Thread 0 prints 9
Thread 1 prints 9
Thread 2 prints 9
Thread 0 prints 10
Thread 1 prints 10
Thread 2 prints 10
    
    
     * ASSIGNMENT (PROJECT CLASS 01)
     *
     * Write more ways of achieving the above goal.
     *
     * BONUS:
     *  Find a way to exactly print (i.e. strict round-robin order)
     *
     *  Thread 1 - 1
     *  Thread 2 - 1
     *  Thread 3 - 1
     *  Thread 1 - 2
     *  Thread 2 - 2
     *  Thread 3 - 2
     */
}
