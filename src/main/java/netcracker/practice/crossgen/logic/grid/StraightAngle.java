package netcracker.practice.crossgen.logic.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StraightAngle implements Angle {

        @Override
        public List<Word> findGridWords(Grid grid) {
            List<Word> gridWords = new ArrayList<>();

            for (int i = 0; i < grid.getHeight(); i++) {
                gridWords.add(new GridWord(i, 0,
                        Direction.HORIZONTAL, grid.getWidth()));
            }

            for (int j = 0; j < grid.getWidth(); j++) {
                gridWords.add(new GridWord(0, j,
                        Direction.VERTICAL, grid.getHeight()));
            }

            return gridWords;
        }

        @Override
        public Map<Word, Map<Word, Integer>> findGridIntersections(Grid grid) {
            List<Word> words = this.findGridWords(grid);
            Map<Word, Map<Word, Integer>> gridIntersections = new HashMap<>();

            for (Word word1 : words) {
                Map<Word, Integer> intersectingWords = new HashMap<>();
                for (Word word2 : words)
                    if (word1.isOrthogonal(word2)) {
                        int col = word1.getCol();
                        int row = word2.getRow();
                        switch (word1.getDirection()) {
                            case VERTICAL:
                                intersectingWords.put(word2, row - word1.getRow());
                                break;
                            case HORIZONTAL:
                                intersectingWords.put(word2, col - word1.getCol());
                                break;
                        }
                        // necessary check for consrained grid
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

}
