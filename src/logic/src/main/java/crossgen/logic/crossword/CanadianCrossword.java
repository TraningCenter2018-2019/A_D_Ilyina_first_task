package crossgen.logic.crossword;

import java.util.List;
import java.util.ArrayList;

import crossgen.logic.grid.Grid;

public class CanadianCrossword extends Grid {
    private List<Clue> clues = new ArrayList<>();

    public CanadianCrossword(int width, int height) {
        super(width, height);
    }

    //needs to be fixed
    public List<Clue> getClues() {
        return this.clues;
    }

    public void addClue(Clue clue) {
        clues.add(clue);
    }
}
