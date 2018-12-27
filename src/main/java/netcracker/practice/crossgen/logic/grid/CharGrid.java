package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;

import java.util.Arrays;

public class CharGrid implements Grid {

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

    public char getSymbol(int row, int col) {
        return symbols[row][col];
    }

    public void setSymbol(int row, int col, char symbol) {
        symbols[row][col] = symbol;
    }

    public void place(String s, Word word) {
        word.getAngle().placeWordInGrid(s, word, this);
    }

    @Override
    public String stringify() {
        return toString();
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
