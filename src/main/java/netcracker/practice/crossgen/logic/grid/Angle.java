package netcracker.practice.crossgen.logic.grid;

import java.util.List;

public interface Angle {

    int getIntersectingWordCol(Word intersectedWord, int position1, int position2);

    int getIntersectingWordRow(Word intersectedWord, int position1, int position2);

    Word getRandomWord(Grid grid, int length);

    List<Word> findGridWords(Grid grid);

    boolean fitsWithinBounds(Word word, Grid grid);

    void placeStringInGrid(String s, Word word, Grid grid);

    void placeWordInGrid(String s, Word word, Grid grid);

    void setProhibitedBorders(Word word, Grid grid);

    boolean wordConflictsGrid(String s, Word word, Grid grid);

}
