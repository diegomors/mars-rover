package marsrover.entities;

import java.util.List;

import marsrover.entities.enums.MovementAction;

public class Movement {
    private List<MovementAction> sequence;
    private int currentIndex;

    public Movement(List<MovementAction> sequence) {
        this.sequence = sequence;
        this.currentIndex = -1;
    }

    public boolean hasNextAction(boolean change) {
        if (currentIndex < sequence.size() - 1) {
            if (change) currentIndex += 1;
            return true;
        }
        return false;
    }

    public MovementAction getCurrentAction() {
        return sequence.get(currentIndex);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.sequence.size(); i++) {
            result.append(this.currentIndex == i ?
                String.format("[%s]", this.sequence.get(i).toString())
                : this.sequence.get(i));
        }
        return result.toString();
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
