package crossgen.logic.generator;

import java.util.*;

import crossgen.logic.grid.Angle;
import crossgen.logic.grid.Grid;
import crossgen.logic.grid.Word;

class CanadianCrosswordGenerator implements Generator {
    //Angle angle;
    Map<Word, Map<Integer, Word>> gridIntersections = new HashMap<>();
    Map<String, Map<Integer, String>> wordIntersections = new HashMap<>();
    Map<Word, List<Intersection>> intersections = new HashMap<>();

    public CanadianCrosswordGenerator(Angle angle, Grid grid, Map<String, String> wordClues) {
        computeGridIntersections(angle, grid);
        computeWordIntersections(wordClues.keySet());
        mapIntersections();
    }

    private void computeGridIntersections(Angle angle, Grid grid) {
        List<Word> gridWords = angle.findGridWords(grid);
        /*
        for (int i = 0; i < gridWords.size(); i++) {
            Word word1 = gridWords.get(i);
            Map<Integer, Word> intersections = new HashMap<Integer, Word>();
            for (int j = i + 1; j < gridWords.size(); j++) {
                Word word2 = gridWords.get(j);
                if (word1.isOrthogonal(word2))

            }
            gridIntersections.put();
        }
        */

        // TODO : implement logic to get all intersecting gridWords
    }

    private void computeWordIntersections(Set<String> words) {
        // TODO : implement logic to get all intersecting words (same chars)
    }

    private void mapIntersections() {
        // TODO : decide if I want to use Map<Word, List<Intersection>> or smth else
    }

    @Override
    public Grid generate() {
        // TODO : implement generator logic

        return null;
    }
}
