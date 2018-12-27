package netcracker.practice.crossgen.logic.generator;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;
import netcracker.practice.crossgen.logic.crossword.Clue;
import netcracker.practice.crossgen.logic.grid.Grid;
import netcracker.practice.crossgen.logic.grid.CharGrid;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

class Solution {

    private final CharGrid charGrid;
    private final Map<String, Clue> cluesInGrid = new HashMap<>();

    public Solution(Grid grid) {
        this.charGrid = new CharGrid(grid);
    }

    public void add(Clue clue) {
        clue.getAngle().placeWordInGrid(clue.getAnswer(), clue, charGrid);
        cluesInGrid.put(clue.getAnswer(), clue);
    }

    public boolean conflicts(Clue clue) {
        return clue.getAngle().wordConflictsGrid(clue.getAnswer(), clue, charGrid);
    }

    public boolean contains(String word) {
        return cluesInGrid.keySet().contains(word);
    }

    public boolean contains(Clue clue) {
        return cluesInGrid.keySet().contains(clue.getAnswer());
    }

    public boolean fitsWithinBounds(Clue clue) {
        return clue.getAngle().fitsWithinBounds(clue, charGrid);
    }

    public int score() {
        return cluesInGrid.size();
    }

    public CanadianCrossword toCrossword() {
        Grid grid = charGrid.getGrid();
        CanadianCrossword crossword = new CanadianCrossword(grid.getHeight(), grid.getWidth());
        crossword.addAllClues(cluesInGrid.values());
        return crossword;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;
        return charGrid.equals(solution.charGrid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charGrid);
    }

    @Override
    public String toString() {
        return charGrid.stringify();
    }

}
