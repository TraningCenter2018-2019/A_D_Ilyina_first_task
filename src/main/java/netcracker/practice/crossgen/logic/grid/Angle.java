package netcracker.practice.crossgen.logic.grid;

import java.util.List;

public interface Angle {

    int getIntersectingWordCol(Word intersectedWord, int position1, int position2);

    int getIntersectingWordRow(Word intersectedWord, int position1, int position2);

    Word getRandomWord(Grid grid, int length);

    List<Word> findGridWords(Grid grid);

    boolean fitsWithinBounds(Word word, Grid grid);

    void putWordInGrid(Word word, MutableGrid grid);

    void setConstrainedBorders(Word word, MutableGrid grid);

    boolean wordConflictsGrid(Word word, MutableGrid grid);

}
