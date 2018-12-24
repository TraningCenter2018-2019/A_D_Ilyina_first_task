package netcracker.practice.crossgen.logic.grid;

import java.util.*;

public interface Angle {

    List<Word> findGridWords(Grid grid);
    Map<Word, Map<Word, Integer>> findGridIntersections(Grid grid);

}
