package netcracker.practice.crossgen.logic.generator;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;
import netcracker.practice.crossgen.logic.crossword.Clue;
import netcracker.practice.crossgen.logic.grid.Grid;

import java.util.Set;
import java.util.HashSet;

class Solution {

    private final CanadianCrossword crossword;
    private final Set<String> wordsInCrossword = new HashSet<>();

    public Solution(Grid grid) {
        this.crossword = (CanadianCrossword) grid;
    }

    public void add(Clue clue) {
        crossword.addClue(clue);
        clue.getDirection().getAngle().placeWord(clue, clue.getWord(), crossword);
        wordsInCrossword.add(clue.getWord());
    }

    public boolean conflicts(Clue clue) {
        return clue.getDirection().getAngle().wordConflictsGrid(clue, clue.getWord(), crossword);
    }

    public boolean contains(Clue clue) {
        return wordsInCrossword.contains(clue.getWord());
    }

    public boolean fitsWithinBounds(Clue clue) {
        return clue.getDirection().getAngle().fitsWithinBounds(clue, crossword);
    }

    public int score() {
        return crossword.cluesCount();
    }

    public CanadianCrossword toCrossword() {
        return crossword;
    }

}
