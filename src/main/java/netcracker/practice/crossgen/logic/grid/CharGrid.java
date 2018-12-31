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

    /*
    public CharGrid(int height, int width) {
        this.grid = new BaseGrid(height, width);
        this.symbols = new char[grid.getHeight()][grid.getWidth()];
        clear();
    }

    public CharGrid(int height, int width, Set<Coordinate> constraints) {
        this.grid = new BaseGrid(height, width, constraints);
        this.symbols = new char[grid.getHeight()][grid.getWidth()];
        clear();
    }*/

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

    public Grid getGrid() {
        return grid;
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
        return symbols[row][col];
    }

    public void fillWithConstraints() {
        for (char[] row : symbols)
            Arrays.fill(row, Settings.CONSTRAINED_SYMBOL);
    }

    @Override
    public void setSymbol(int row, int col, char symbol) {
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
