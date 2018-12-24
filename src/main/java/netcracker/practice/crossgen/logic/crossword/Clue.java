package netcracker.practice.crossgen.logic.crossword;

import netcracker.practice.crossgen.logic.grid.Word;
import netcracker.practice.crossgen.logic.grid.Direction;

class Clue extends Word {
    private int clueNumber = 0;
    private final String clue;
    private final String word;

    public Clue(int row, int col, Direction direction, String word, String clue) {
        super(row, col, direction);
        this.word = word;
        this.clue = clue;
    }

    public int getClueNumber() {
        return this.clueNumber;
    }

    public void setClueNumber(int clueNumber) {
        this.clueNumber = clueNumber;
    }

    @Override
    public int getWordLength() {
        return this.word.length();
    }
}
