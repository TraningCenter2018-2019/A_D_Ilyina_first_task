package crossgen.logic.grid;

import java.util.Objects;

public abstract class Word {
    private int row;
    private int col;
    private Direction direction;

    protected Word(int row, int col, Direction direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public abstract int getWordLength();

    public boolean isOrthogonal(Word word) {
        return this.direction.isOrthogonal(word.direction);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Word)) return false;

        Word word = (Word) object;
        return word.row == this.row &&
                word.col == this.col &&
                word.direction == this.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, direction);
    }
}
