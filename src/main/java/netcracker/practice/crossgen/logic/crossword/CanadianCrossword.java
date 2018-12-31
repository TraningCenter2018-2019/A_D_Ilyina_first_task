package netcracker.practice.crossgen.logic.crossword;

import java.util.*;

import netcracker.practice.crossgen.logic.grid.BaseGrid;
import netcracker.practice.crossgen.logic.grid.CharGrid;
import netcracker.practice.crossgen.logic.grid.Coordinate;
import netcracker.practice.crossgen.logic.word.Clue;
import netcracker.practice.crossgen.logic.word.ClueComparator;

public class CanadianCrossword extends BaseGrid {

    private final TreeSet<Clue> clues = new TreeSet<>(new ClueComparator());

    public CanadianCrossword(int height, int width) {
        super(height, width);
    }

    public CanadianCrossword(int height, int width, Set<Coordinate> constraints) {
        super(height, width, constraints);
    }

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public void addAllClues(Collection<Clue> clues) {
        for (Clue clue : clues)
            this.clues.add(clue);
    }

    public void clear() {
        clues.clear();
    }

    public int cluesCount() {
        return clues.size();
    }

    public Set<Clue> getClues() {
        return this.clues;
    }

    public void setClueNumbers() {
        ClueComparator comparator = new ClueComparator();
        Clue previous = clues.first();
        for (Clue clue :clues) {
            int number = comparator.compare(clue, previous) == 0 ?
                    previous.getClueNumber() :
                    previous.getClueNumber() + 1;
            clue.setClueNumber(number);
        }
    }

    @Override
    public char getSymbol(int row, int col) {
        return getCharGrid().getSymbol(row, col);
    }

    private CharGrid getCharGrid() {
        CharGrid charGrid = new CharGrid(this);
        for (Clue clue : clues)
            charGrid.put(clue);
        return charGrid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CanadianCrossword)) return false;
        return super.equals(o) && clues.equals(((CanadianCrossword) o).clues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clues);
    }

    @Override
    public String toString() {
        return getCharGrid().toString();
    }

}
