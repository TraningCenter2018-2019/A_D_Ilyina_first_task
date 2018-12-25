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
        return new Word(randomWord.getRow(), col, Direction.HORIZONTAL) {
            @Override
            public int getLength() {
                return length;
            }
        };
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

        /*
        for (int i = 0; i < grid.getHeight(); i++) {
            gridWords.add(new GridWord(i, 0,
                    Direction.HORIZONTAL, grid.getWidth()));
        }

        for (int j = 0; j < grid.getWidth(); j++) {
            gridWords.add(new GridWord(0, j,
                    Direction.VERTICAL, grid.getHeight()));
        }
        */

        return gridWords;
    }


    @Override
    public Map<Word, Map<Word, Integer>> findGridWordIntersections(Grid grid) {
        List<Word> words = this.findGridWords(grid);
        Map<Word, Map<Word, Integer>> gridIntersections = new HashMap<>();

        for (Word word1 : words) {
            Map<Word, Integer> intersectingWords = new HashMap<>();
            for (Word word2 : words)
                if (word1.isOrthogonal(word2)) {
                    switch (word1.getDirection()) {
                        case VERTICAL:
                            intersectingWords.put(word2, word2.getRow() - word1.getRow());
                            break;
                        case HORIZONTAL:
                            intersectingWords.put(word2, word2.getCol() - word1.getCol());
                            break;
                    }
                    // necessary check for constrained grid
                /*
                    if (col > word2.getWordLength() - word2.getCol() &&
                        col <= word2.getCol() &&
                        row > word1.getWordLength() - word1.getRow() &&
                        row <= word1.getRow()) {
                    switch (word1.getDirection()) {
                        case VERTICAL:
                            intersectingWords.put(word2, row - word1.getRow());
                            break;
                        case HORIZONTAL:
                            intersectingWords.put(word2, col - word1.getCol());
                            break;
                    }
                }*/
                }
            if (intersectingWords.size() > 0)
                gridIntersections.put(word1, intersectingWords);
        }

        return gridIntersections;
    }


    @Override
    public boolean fitsWithinBounds(Word word, Grid grid) {
        switch (word.getDirection()) {
            case HORIZONTAL:
                if (word.getCol() + word.getLength() > grid.getWidth())
                    return false;
            case VERTICAL:
                if (word.getRow() + word.getLength() > grid.getHeight())
                    return false;
        }
        return true;
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
    public boolean wordConflictsGrid(Word word, String wordString, Grid grid) {
        int col = word.getCol(), row = word.getRow(), length = word.getLength();
        switch (word.getDirection()) {
            case HORIZONTAL:
                for (int i = col; i < col + length; i++) {
                    char gridSymbol = grid.getSymbol(row, i);
                    if (!(gridSymbol == Settings.EMPTY_SYMBOL || wordString.charAt(i - col) == gridSymbol))
                        return true;
                }
            case VERTICAL:
                for (int i = row; i < row + length; i++) {
                    char gridSymbol = grid.getSymbol(i, col);
                    if (!(gridSymbol == Settings.EMPTY_SYMBOL || wordString.charAt(i - row) == gridSymbol))
                        return true;
                }
        }
        return false;
    }


    @Override
    public String wordToString(Word word, Grid grid) {
        StringBuilder sb = new StringBuilder();

        switch (word.getDirection()) {
            case HORIZONTAL:
                for (int col = word.getCol(), row = word.getRow(), n = word.getLength(), width = grid.getWidth();
                     col < n && col < width; col++)
                    sb.append(grid.getSymbol(row, col));
                break;
            case VERTICAL:
                for (int col = word.getCol(), row = word.getRow(), n = word.getLength(), height = grid.getHeight();
                     row < n && row < height; row++)
                    sb.append(grid.getSymbol(row, col));
                break;
        }

        return sb.toString();
    }

}
