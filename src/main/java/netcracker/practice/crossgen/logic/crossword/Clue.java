package netcracker.practice.crossgen.logic.crossword;

import netcracker.practice.crossgen.logic.grid.Angle;
import netcracker.practice.crossgen.logic.grid.Direction;
import netcracker.practice.crossgen.logic.grid.GridWord;
import netcracker.practice.crossgen.logic.grid.Word;

import java.util.Objects;

public class Clue implements Word {

    private int clueNumber = 0;
    private final GridWord gridWord;
    private final String answer;
    private final String clue;

    public Clue(GridWord gridWord, String answer, String clue) {
        this.gridWord = gridWord;
        this.answer = answer;
        this.clue = clue;
    }

    public Clue(int row, int col, Direction direction, String answer, String clue) {
        this.gridWord = new GridWord(row, col, direction, answer.length());
        this.answer = answer;
        this.clue = clue;
    }

    @Override
    public Angle getAngle() {
        return gridWord.getAngle();
    }

    public String getAnswer() {
        return answer;
    }

    public char getCharAt(int position) {
        return answer.charAt(position);
    }

    public int getClueNumber() {
        return this.clueNumber;
    }

    public void setClueNumber(int clueNumber) {
        this.clueNumber = clueNumber;
    }

    @Override
    public int getCol() {
        return gridWord.getCol();
    }

    @Override
    public Direction getDirection() {
        return gridWord.getDirection();
    }

    @Override
    public int getRow() {
        return gridWord.getRow();
    }

    @Override
    public int getLength() {
        return gridWord.getLength();
    }

    @Override
    public boolean isOrthogonal(Word word) {
        return gridWord.isOrthogonal(word);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Clue)) return false;

        Clue clue = (Clue) o;
        return answer == clue.answer && this.clue == clue.clue && gridWord.equals(clue.gridWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer.hashCode(), clue.hashCode(), gridWord.hashCode());
    }
}
