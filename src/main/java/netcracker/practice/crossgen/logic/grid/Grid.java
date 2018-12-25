package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;

import java.util.Arrays;

public abstract class Grid {

    private final char[][] symbols;

    protected Grid(int height, int width) {
        this.symbols = new char[height][width];

        for (char[] row : symbols)
            Arrays.fill(row, Settings.EMPTY_SYMBOL);
    }

    public int getHeight() {
        return symbols.length;
    }

    public char getSymbol(int row, int col) {
        return symbols[row][col];
    }

    public void setSymbol(int row, int col, char symbol) {
        symbols[row][col] = symbol;
    }

    public int getWidth() {
        return symbols[0].length;
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
