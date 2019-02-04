package netcracker.practice.crossgen.logic.angle;

import netcracker.practice.crossgen.Settings;
import netcracker.practice.crossgen.logic.grid.*;
import netcracker.practice.crossgen.logic.word.Direction;
import netcracker.practice.crossgen.logic.word.GridWord;
import netcracker.practice.crossgen.logic.word.Word;

import java.util.*;

public class StraightAngle implements Angle {
    @Override
    public Set<Coordinate> getCoordinates(Word word) {
        HashSet<Coordinate> coordinates = new HashSet<>();
        final int col = word.getCol(), row = word.getRow(), length = word.getLength();
        switch (word.getDirection()) {
            case HORIZONTAL:
                final int endCol = col + length - 1;
                for (int c = col; c <= endCol; c++)
                    coordinates.add(new Coordinate(row, c));
                break;
            case VERTICAL:
                final int endRow = row + length - 1;
                for (int r = row; r <= endRow; r++)
                    coordinates.add(new Coordinate(r, col));
                break;
        }
        return coordinates;
    }

    @Override
    public int getEndCol(Word word) {
        return word.getDirection() == Direction.VERTICAL ?
                word.getCol() : word.getCol() + word.getLength() - 1;
    }

    @Override
    public int getEndRow(Word word) {
        return word.getDirection() == Direction.HORIZONTAL ?
                word.getRow() : word.getRow() + word.getLength() - 1;
    }

    @Override
    public int getIntersectingWordCol(Word intersectedWord, int position1, int position2) {
        int col = -1;
        switch (intersectedWord.getDirection()) {
            case HORIZONTAL:
                col = intersectedWord.getCol() + position1;
                break;
            case VERTICAL:
                col = intersectedWord.getCol() - position2;
                break;
        }
        return col;
    }

    @Override
    public int getIntersectingWordRow(Word intersectedWord, int position1, int position2) {
        int row = -1;
        switch (intersectedWord.getDirection()) {
            case HORIZONTAL:
                row = intersectedWord.getRow() - position2;
                break;
            case VERTICAL:
                row = intersectedWord.getRow() + position1;
                break;
        }
        return row;
    }

    @Override
    public Word getRandomWord(Grid grid, int length) {
        List<Word> gridWords = findGridWords(grid);
        Word randomWord = null;

        while (randomWord == null) {
            Collections.shuffle(gridWords);
            if (gridWords.get(0).getLength() >= length)
                randomWord = gridWords.get(0);
        }

        int col = (new Random()).nextInt(randomWord.getLength() - length + 1);
        return randomWord;
    }

    @Override
    public List<Word> findGridWords(Grid grid) {
        List<Word> gridWords = new ArrayList<>();

        for (int row = 0, length = 1; row < grid.getHeight(); row++)
            for (int col = 0, width = grid.getWidth(); col < width; col++) {
                if (grid.getSymbol(row, col) == Settings.CONSTRAINED_SYMBOL || col == width - 1) {
                    if (length > 1)
                        gridWords.add(new GridWord(row, col - length + 1, Direction.HORIZONTAL, length));
                    length = 1;
                    continue;
                }
                length++;
            }

        for (int col = 0, length = 1; col < grid.getWidth(); col++)
            for (int row = 0, height = grid.getHeight(); row < height; row++) {
                if (grid.getSymbol(row, col) == Settings.CONSTRAINED_SYMBOL || row == height - 1) {
                    if (length > 1)
                        gridWords.add(new GridWord(row - length + 1, col, Direction.VERTICAL, length));
                    length = 1;
                    continue;
                }
                length++;
            }

        return gridWords;
    }

    @Override
    public boolean fitsWithinBounds(Word word, Grid grid) {
        if (word.getCol() < 0 || word.getRow() < 0)
            return false;
        switch (word.getDirection()) {
            case HORIZONTAL:
                if (word.getCol() + word.getLength() > grid.getWidth())
                    return false;
                break;
            case VERTICAL:
                if (word.getRow() + word.getLength() > grid.getHeight())
                    return false;
                break;
        }
        return true;
    }

    @Override
    public void putWordInGrid(Word word, MutableGrid grid) {
        switch (word.getDirection()) {
            case HORIZONTAL:
                for (int i = 0, row = word.getRow(), col = word.getCol(); i <  word.getLength(); i++) {
                    grid.setSymbol(row, i + col, word.getString().charAt(i));
                }
                break;
            case VERTICAL:
                for (int i = 0, row = word.getRow(), col = word.getCol(); i <  word.getLength(); i++) {
                    grid.setSymbol(i + row, col, word.getString().charAt(i));
                }
                break;
        }
    }

    @Override
    public void setConstrainedBorders(Word word, MutableGrid grid) {
        switch (word.getDirection()) {
            case HORIZONTAL:
                if (word.getCol() > 0)
                    grid.setSymbol(word.getRow(), word.getCol() - 1, Settings.CONSTRAINED_SYMBOL);
                if (word.getCol() + word.getLength() < grid.getWidth())
                    grid.setSymbol(word.getRow(), word.getCol() + word.getLength(), Settings.CONSTRAINED_SYMBOL);
                break;
            case VERTICAL:
                if (word.getRow() > 0)
                    grid.setSymbol(word.getRow() - 1, word.getCol(), Settings.CONSTRAINED_SYMBOL);
                if (word.getRow() + word.getLength() < grid.getHeight())
                    grid.setSymbol(word.getRow() + word.getLength(), word.getCol(), Settings.CONSTRAINED_SYMBOL);
                break;
        }
    }

    @Override
    public boolean wordConflictsGrid(Word word, MutableGrid grid) {
        final int col = word.getCol(), row = word.getRow(), length = word.getLength();
        switch (word.getDirection()) {
            case HORIZONTAL:
                final int endCol = col + length - 1;
                if ((col > 0 && !(
                        grid.getSymbol(row, col - 1) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(row, col - 1) == Settings.CONSTRAINED_SYMBOL)) ||
                        (endCol < grid.getWidth() - 1 && !(
                        grid.getSymbol(row, endCol + 1) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(row, endCol + 1) == Settings.CONSTRAINED_SYMBOL)))
                    return true;
                for (int i = col; i <= endCol; i++) {
                    char gridSymbol = grid.getSymbol(row, i);
                    if (!(gridSymbol == Settings.EMPTY_SYMBOL || word.getString().charAt(i - col) == gridSymbol))
                        return true;
                }
                break;
            case VERTICAL:
                final int endRow = row + length - 1;
                if ((row > 0 && !(
                        grid.getSymbol(row - 1, col) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(row - 1, col) == Settings.CONSTRAINED_SYMBOL)) ||
                        (endRow < grid.getHeight() - 1 && !(
                        grid.getSymbol(endRow + 1, col) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(endRow + 1, col) == Settings.CONSTRAINED_SYMBOL)))
                    return true;
                for (int i = row; i <= endRow; i++) {
                    char gridSymbol = grid.getSymbol(i, col);
                    if (!(gridSymbol == Settings.EMPTY_SYMBOL || word.getString().charAt(i - row) == gridSymbol))
                        return true;
                }
                break;
        }
        return false;
    }

}
