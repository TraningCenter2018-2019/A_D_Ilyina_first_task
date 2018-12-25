package netcracker.practice.crossgen.logic.grid;

import java.util.List;
import java.util.Map;

public interface Angle {

    int getIntersectingWordCol(Word intersectedWord, int position1, int position2);

    int getIntersectingWordRow(Word intersectedWord, int position1, int position2);

    Word getRandomWord(Grid grid, int length);

    List<Word> findGridWords(Grid grid);

    Map<Word, Map<Word, Integer>> findGridWordIntersections(Grid grid);

    boolean fitsWithinBounds(Word word, Grid grid);

    void placeStringInGrid(String str, int row, int col, Grid grid);

    void placeWord(Word word, String wordString, Grid grid);

    void setProhibitedBorders(Word word, Grid grid);

    boolean wordConflictsGrid(Word word, String wordString, Grid grid);

    String wordToString(Word word, Grid grid);

}
