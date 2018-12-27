package netcracker.practice.crossgen.logic.grid;

import java.util.Objects;

public class GridWord implements Word {

    private final int row;
    private final int col;
    private final Direction direction;
    private final int length;

    public GridWord(int row, int col, Direction direction, int length) {
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.length = length;
    }

    @Override
    public Angle getAngle() {
        return direction.getAngle();
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public boolean isOrthogonal(Word word) {
        return this.direction.isOrthogonal(word.getDirection());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof GridWord)) return false;

        GridWord gridWord = (GridWord) o;
        return gridWord.row == this.row &&
                gridWord.col == this.col &&
                gridWord.direction == this.direction &&
                gridWord.length == this.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, direction, length);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ") " + direction.toString() + " length: " + getLength();
    }

}
