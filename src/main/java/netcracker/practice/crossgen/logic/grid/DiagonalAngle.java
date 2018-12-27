package netcracker.practice.crossgen.logic.grid;

import java.util.List;
import java.util.ArrayList;

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

        return gridWords;
    }

    @Override
    public boolean fitsWithinBounds(Word word, Grid grid) {
        return false;
    }


    @Override
    public void placeStringInGrid(String s, Word word, Grid grid) {

    }

    @Override
    public void placeWordInGrid(String s, Word word, Grid grid) {

    }

    @Override
    public void setProhibitedBorders(Word word, Grid grid) {

    }

    @Override
    public boolean wordConflictsGrid(String s, Word word, Grid grid) {
        return true;
    }

}
