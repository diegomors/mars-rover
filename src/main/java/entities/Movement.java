package entities;

import java.util.List;
import java.util.stream.Collectors;

public class Movement {
    private List<String> sequence;
    private int currentIndex;

    public Movement(List<String> sequence) {
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

    public String getCurrent() {
        return sequence.get(currentIndex);
    }

    @Override
    public String toString() {
        String result = this.sequence.stream()
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
