package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;
import netcracker.practice.crossgen.logic.word.Word;

import java.util.Arrays;
import java.util.Set;

public class CharGrid implements MutableGrid {

    private final Grid grid;
    private final char[][] symbols;

    public CharGrid(Grid grid) {
        this.grid = grid;
        this.symbols = new char[grid.getHeight()][grid.getWidth()];
        clear();
    }

    public void clear() {
        for (char[] row : symbols)
            Arrays.fill(row, Settings.EMPTY_SYMBOL);

        if (grid.getConstraints() != null) {
            for (Coordinate c : grid.getConstraints())
                symbols[c.getRow()][c.getCol()] = Settings.CONSTRAINED_SYMBOL;
        }
    }

    @Override
    public Set<Coordinate> getConstraints() {
        return grid.getConstraints();
    }

    @Override
    public int getHeight() {
        return symbols.length;
    }

    @Override
    public int getWidth() {
        return symbols[0].length;
    }

    @Override
    public char getSymbol(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col > getWidth())
            throw new IllegalArgumentException("Symbol coordinates must be inside the grid.");
        return symbols[row][col];
    }

    @Override
    public void setSymbol(int row, int col, char symbol) {
        if (row < 0 || row >= getHeight() || col < 0 || col > getWidth())
            throw new IllegalArgumentException("Symbol coordinates must be inside the grid.");
        symbols[row][col] = symbol;
    }

    @Override
    public void put(Word word) {
        word.getAngle().putWordInGrid(word, this);
    }

    @Override
    public void setConstrainedBorders(Word word) {
        word.getAngle().setConstrainedBorders(word, this);
    }

    @Override
    public char[][] toCharMatrix() {
        return symbols.clone();
    }

    @Override
    public String toString() {
        String lineSeparator = System.lineSeparator();
        StringBuilder sb = new StringBuilder();

        for (char[] row : symbols) {
            for (char symbol : row)
                sb.append(symbol);
            sb.append(lineSeparator);
        }

        return sb.toString();
    }

}
