package netcracker.practice.crossgen.logic.crossword;

import netcracker.practice.crossgen.logic.grid.Word;
import netcracker.practice.crossgen.logic.grid.Direction;

public class Clue extends Word {

    private int clueNumber = 0;
    private String clue;
    private String word;

    public Clue(int row, int col, Direction direction, String word, String clue) {
        super(row, col, direction);
        this.word = word;
        this.clue = clue;
    }

    public char getCharAt(int position) {
        return word.charAt(position);
    }

    public int getClueNumber() {
        return this.clueNumber;
    }

    public void setClueNumber(int clueNumber) {
        this.clueNumber = clueNumber;
    }

    public String getWord() {
        return word;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int getLength() {
        return this.word.length();
    }
}
