package netcracker.practice.crossgen.logic.generator;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;
import netcracker.practice.crossgen.logic.crossword.Clue;
import netcracker.practice.crossgen.logic.grid.CharGrid;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

class Solution {

    private final CharGrid charGrid;
    private final Map<String, Clue> cluesInGrid = new HashMap<>();

    public Solution(CanadianCrossword crossword) {
        this.charGrid = new CharGrid(crossword);
    }

    public void add(Clue clue) {
        charGrid.put(clue);
        charGrid.setConstrainedBorders(clue);
        cluesInGrid.put(clue.getString(), clue);
    }

    public boolean conflicts(Clue clue) {
        return clue.getAngle().wordConflictsGrid(clue, charGrid);
    }

    public boolean contains(String word) {
        return cluesInGrid.keySet().contains(word);
    }

    public boolean contains(Clue clue) {
        return cluesInGrid.keySet().contains(clue.getString());
    }

    public boolean fitsWithinBounds(Clue clue) {
        return clue.getAngle().fitsWithinBounds(clue, charGrid);
    }

    public int score() {
        return cluesInGrid.size();
    }

    public CanadianCrossword toCrossword() {
        CanadianCrossword crossword = (CanadianCrossword) charGrid.getGrid();
        crossword.addAllClues(cluesInGrid.values());
        crossword.setClueNumbers();
        return crossword;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;
        return cluesInGrid.equals(solution.cluesInGrid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charGrid);
    }

    @Override
    public String toString() {
        return charGrid.toString();
    }

}
