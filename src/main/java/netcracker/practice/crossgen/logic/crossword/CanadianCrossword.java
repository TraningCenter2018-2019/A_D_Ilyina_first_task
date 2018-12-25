package netcracker.practice.crossgen.logic.crossword;

import java.util.List;
import java.util.ArrayList;

import netcracker.practice.crossgen.logic.grid.Grid;

public class CanadianCrossword extends Grid {
    private final List<Clue> clues = new ArrayList<>();

    public CanadianCrossword(int height, int width) {
        super(height, width);
    }

    public List<Clue> getClues() {
        return this.clues;
    }

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public int cluesCount() {
        return clues.size();
    }
}
