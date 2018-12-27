package netcracker.practice.crossgen.logic.crossword;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import netcracker.practice.crossgen.logic.grid.CharGrid;
import netcracker.practice.crossgen.logic.grid.BaseGrid;

public class CanadianCrossword extends BaseGrid {

    private final List<Clue> clues = new ArrayList<>();

    public CanadianCrossword(int height, int width) {
        super(height, width);
    }

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public void addAllClues(Collection<Clue> clues) {
        //clues.addAll(clues);
        for (Clue clue : clues)
            this.clues.add(clue);
    }

    public int cluesCount() {
        return clues.size();
    }

    public List<Clue> getClues() {
        return this.clues;
    }

    @Override
    public char getSymbol(int row, int col) {
        return getCharGrid().getSymbol(row, col);
    }

    @Override
    public void setSymbol(int row, int col, char symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String stringify() {
        return getCharGrid().toString();
    }

    private CharGrid getCharGrid() {
        CharGrid cg = new CharGrid(this);
        for (Clue clue : clues)
            cg.place(clue.getAnswer(), clue);
        return cg;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CanadianCrossword)) return false;

        CanadianCrossword cross = (CanadianCrossword) o;
        return super.equals(o) && clues.containsAll(cross.clues) && cross.clues.containsAll(clues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clues);
    }

    @Override
    public String toString() {
        return super.toString() + " clues count: " + cluesCount();
    }
}
