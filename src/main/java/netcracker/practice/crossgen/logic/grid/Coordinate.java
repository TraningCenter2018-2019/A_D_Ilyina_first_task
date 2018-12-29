package netcracker.practice.crossgen.logic.grid;

import java.util.Objects;

public class Coordinate {

    private final int row, col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Coordinate)) return false;
        return ((Coordinate) o).row == row && ((Coordinate) o).col == col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

}
