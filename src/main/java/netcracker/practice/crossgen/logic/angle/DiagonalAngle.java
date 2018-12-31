package netcracker.practice.crossgen.logic.angle;

import netcracker.practice.crossgen.logic.grid.Grid;
import netcracker.practice.crossgen.logic.grid.MutableGrid;
import netcracker.practice.crossgen.logic.word.Word;

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
    public void putWordInGrid(Word word, MutableGrid grid) {

    }

    @Override
    public void setConstrainedBorders(Word word, MutableGrid grid) {

    }

    @Override
    public boolean wordConflictsGrid(Word word, MutableGrid grid) {
        return true;
    }

}
