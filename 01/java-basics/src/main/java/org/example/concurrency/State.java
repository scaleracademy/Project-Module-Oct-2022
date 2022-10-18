package org.example.concurrency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class State {
    private Integer current = 0;
    private final Integer nStates;

    public State(Integer nStates) {
        this.nStates = nStates;
    }

    public void changeState() {
        current = (current + 1) % nStates;
    }
}
