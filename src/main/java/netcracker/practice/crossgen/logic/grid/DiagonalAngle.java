package netcracker.practice.crossgen.logic.grid;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class DiagonalAngle implements Angle {

    @Override
    public int getIntersectingWordCol(Word intersectedWord, int position1, int position2) {
        return -1;
    }

    @Override
    public int getIntersectingWordRow(Word intersectedWord, int position1, int position2) {
        return -1;
    }


    @Override
    public Word getRandomWord(Grid grid, int length) {
        return null;
    }


    @Override
    public List<Word> findGridWords(Grid grid) {
        List<Word> gridWords = new ArrayList<>();

        // TODO : implement diagonal strategy

        return gridWords;
    }


    @Override
    public Map<Word, Map<Word, Integer>> findGridWordIntersections(Grid grid) {

        // TODO : implement diagonal strategy

        return null;
    }


    @Override
    public boolean fitsWithinBounds(Word word, Grid grid) {
        return false;
    }


    @Override
    public void setProhibitedBorders(Word word, Grid grid) {

    }


    @Override
    public boolean wordConflictsGrid(Word word, String wordString, Grid grid) {
        return true;
    }


    @Override
    public String wordToString(Word word, Grid grid) {
        StringBuilder sb = new StringBuilder();

        switch (word.getDirection()) {
            case DIAGONAL:
                for (int col = word.getCol(), row = word.getRow(), n = word.getLength(),
                     width = grid.getWidth(), height = grid.getHeight();
                     row < n + word.getRow() && col < n + word.getCol() && row < height && col < width;
                     col++, row++)
                    sb.append(grid.getSymbol(row, col));
                break;
            case ANTI_DIAGONAL:
                for (int col = word.getCol(), row = word.getRow(), n = word.getLength(),
                     width = grid.getWidth(), height = grid.getHeight();
                     row > n - word.getRow() && col < n + word.getCol() && row >= 0 && col < width;
                     col++, row--)
                    sb.append(grid.getSymbol(row, col));
                break;
        }

        return sb.toString();
    }

}
