package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;

import java.util.*;

public class StraightAngle implements Angle {

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
                if (grid.getSymbol(row, col) == Settings.PROHIBITED_SYMBOL || col == width - 1) {
                    if (length > 1)
                        gridWords.add(new GridWord(row, col - length + 1, Direction.HORIZONTAL, length));
                    length = 1;
                    continue;
                }
                length++;
            }

        for (int col = 0, length = 1; col < grid.getWidth(); col++)
            for (int row = 0, height = grid.getHeight(); row < height; row++) {
                if (grid.getSymbol(row, col) == Settings.PROHIBITED_SYMBOL || row == height - 1) {
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
    public void placeStringInGrid(String s, Word word, Grid grid) {
        switch (word.getDirection()) {
            case HORIZONTAL:
                for (int i = 0, row = word.getRow(), col = word.getCol(); i <  s.length(); i++) {
                    grid.setSymbol(row, i + col, s.charAt(i));
                }
                break;
            case VERTICAL:
                for (int i = 0, row = word.getRow(), col = word.getCol(); i <  s.length(); i++) {
                    grid.setSymbol(i + row, col, s.charAt(i));
                }
                break;
        }
    }

    @Override
    public void placeWordInGrid(String s, Word word, Grid grid) {
        placeStringInGrid(s, word, grid);
        setProhibitedBorders(word, grid);
    }

    @Override
    public void setProhibitedBorders(Word word, Grid grid) {
        switch (word.getDirection()) {
            case HORIZONTAL:
                if (word.getCol() > 0)
                    grid.setSymbol(word.getRow(), word.getCol() - 1, Settings.PROHIBITED_SYMBOL);
                if (word.getCol() + word.getLength() < grid.getWidth())
                    grid.setSymbol(word.getRow(), word.getCol() + word.getLength(), Settings.PROHIBITED_SYMBOL);
                break;
            case VERTICAL:
                if (word.getRow() > 0)
                    grid.setSymbol(word.getRow() - 1, word.getCol(), Settings.PROHIBITED_SYMBOL);
                if (word.getRow() + word.getLength() < grid.getHeight())
                    grid.setSymbol(word.getRow() + word.getLength(), word.getCol(), Settings.PROHIBITED_SYMBOL);
                break;
        }
    }

    @Override
    public boolean wordConflictsGrid(String s, Word word, Grid grid) {
        int col = word.getCol(), row = word.getRow(), length = word.getLength();
        switch (word.getDirection()) {
            case HORIZONTAL:
                int endCol = col + length - 1;
                if ((col > 0 && !(
                        grid.getSymbol(row, col - 1) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(row, col - 1) == Settings.PROHIBITED_SYMBOL)) ||
                        (endCol < grid.getWidth() - 1 && !(
                        grid.getSymbol(row, endCol + 1) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(row, endCol + 1) == Settings.PROHIBITED_SYMBOL)))
                    return true;
                for (int i = col; i <= endCol; i++) {
                    char gridSymbol = grid.getSymbol(row, i);
                    if (!(gridSymbol == Settings.EMPTY_SYMBOL || s.charAt(i - col) == gridSymbol))
                        return true;
                }
                break;
            case VERTICAL:
                int endRow = row + length - 1;
                if ((row > 0 && !(
                        grid.getSymbol(row - 1, col) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(row - 1, col) == Settings.PROHIBITED_SYMBOL)) ||
                        (endRow < grid.getHeight() - 1 && !(
                        grid.getSymbol(endRow + 1, col) == Settings.EMPTY_SYMBOL ||
                        grid.getSymbol(endRow + 1, col) == Settings.PROHIBITED_SYMBOL)))
                    return true;
                for (int i = row; i <= endRow; i++) {
                    char gridSymbol = grid.getSymbol(i, col);
                    if (!(gridSymbol == Settings.EMPTY_SYMBOL || s.charAt(i - row) == gridSymbol))
                        return true;
                }
                break;
        }
        return false;
    }

}
