package netcracker.practice.crossgen.logic.grid;

public class GridWord extends Word {
    private int length;

    public GridWord(int row, int col, Direction direction, int length) {
        super(row, col, direction);
        this.length = length;
    }

    @Override
    public int getWordLength() {
        return this.length;
    }
}
