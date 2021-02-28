package entities;

import java.util.List;
import java.util.stream.Collectors;

import entities.enums.MovementDirection;

public class Movement {
    private List<MovementDirection> sequence;
    private int currentIndex;

    public Movement(List<MovementDirection> sequence) {
        this.sequence = sequence;
        this.currentIndex = -1;
    }

    private boolean hasNext() {
        return currentIndex < sequence.size() - 1;
    }

    public boolean moveToNext() {
        if (hasNext()) {
            currentIndex++;
            return true;
        }
        return false;
    }

    public MovementDirection getCurrent() {
        return sequence.get(currentIndex);
    }

    @Override
    public String toString() {
        String result = this.sequence.stream()
            .map(n -> n.toString())
            .collect(Collectors.joining());

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == Movement.class && ((Movement) o).toString().equals(this.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
