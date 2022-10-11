package org.example.concurrency;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Printer implements Runnable {
    private final State state;

    private Integer currentValue;

    private final Integer finalValue;

    private final Integer myState;

    @Override
    @SneakyThrows
    public void run() {
        while (currentValue <= finalValue) {
            synchronized (state) {
                while (!myState.equals(state.getCurrent())) {
                    state.wait();
                }
                System.out.println(Thread.currentThread().getId() + ": " + currentValue++);
                state.changeState();
                state.notifyAll();
            }
        }
    }
}
