package entities;

import java.util.List;
import java.util.ArrayList;

public class Movement {
    private List<String> sequence;
    private int currentIndex;

    public Movement(ArrayList<String> sequence) {
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
}
