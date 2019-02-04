package netcracker.practice.crossgen.logic.word;

import java.util.Comparator;

public class ClueComparator implements Comparator<Clue> {

    public int compare(Clue clue1, Clue clue2) {
        int result;
        result = Integer.compare(clue1.getRow(), clue2.getRow());
        if(result != 0) return result;
        result = Integer.compare(clue1.getCol(), clue2.getCol());
        if(result != 0) return result;
        result = clue1.getString().compareTo(clue2.getString());
        return result;
    }

}
