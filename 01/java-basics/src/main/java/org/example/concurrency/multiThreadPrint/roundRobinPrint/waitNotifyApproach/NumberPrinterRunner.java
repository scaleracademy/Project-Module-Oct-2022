package org.example.concurrency.multiThreadPrint.roundRobinPrint.waitNotifyApproach;

//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@AllArgsConstructor
public class NumberPrinterRunner implements Runnable {
    private NumberPrinter numberPrinter;
    private int remainder;

    public NumberPrinterRunner(NumberPrinter numberPrinter, int reminder) {
        this.numberPrinter = numberPrinter;
        this.remainder = reminder;
    }

    @Override
    public void run() {
        numberPrinter.printNumbers(remainder);
    }
}
