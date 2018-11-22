package crossgen.logic.generator;

import java.util.*;

import crossgen.logic.grid.Angle;
import crossgen.logic.grid.Direction;
import crossgen.logic.grid.Grid;
import crossgen.logic.grid.Word;

class CanadianCrosswordGenerator implements Generator {
    private Grid grid;
    private Map<String, String> clues;
    private Set<GridWordMatch> gridIntersections = new HashSet<>();
    private Set<WordMatch> wordIntersections = new HashSet<>();
    private List<Intersection> intersections = new ArrayList<>();

    public CanadianCrosswordGenerator(Angle angle, Grid grid, Map<String, String> clues) {
        this.clues = clues;
        this.grid = grid;
        computeGridIntersections(angle);
        computeWordIntersections();
        mapIntersections();
    }

    private void computeGridIntersections(Angle angle) {
        Map<Word, Map<Word, Integer>> gridWordMap = angle.findGridIntersections(grid);
        for (Word gridWord : gridWordMap.keySet()) {
            if (gridWord.getDirection() == Direction.HORIZONTAL) {
                Map<Word, Integer> intersectingWords = gridWordMap.get(gridWord);
                for (Word intersectingWord : intersectingWords.keySet()) {
                    int position1 = intersectingWords.get(intersectingWord);
                    int position2 = gridWordMap.get(intersectingWord).get(gridWord);
                    gridIntersections.add(new GridWordMatch(gridWord, intersectingWord, position1, position2));
                }
            }
        }
    }

    private void computeWordIntersections() {
        for (String word1 : clues.keySet())
            for (String word2 : clues.keySet())
                if (!word1.equals(word2))
                    for (int i = 0; i < word1.length(); i++)
                        for (int j = 0; j < word2.length(); j++)
                            if (word1.charAt(i) == word2.charAt(j))
                                wordIntersections.add(new WordMatch(word1, word2, i, j));
    }

    private void mapIntersections() {
        // TODO : fix an error
        for (GridWordMatch gridIntersection : gridIntersections)
            for (WordMatch wordIntersection : wordIntersections) {
                int position1 = gridIntersection.getPosition1() - wordIntersection.getPosition1();
                int position2 = gridIntersection.getPosition2() - wordIntersection.getPosition2();
                if (position1 >= 0 && position2 >= 0)
                    intersections.add(new Intersection(gridIntersection, wordIntersection,
                            position1, position2));
            }
    }

    @Override
    public Grid generate() {
        // TODO : implement generator logic
        Set<Solution> solutions = new HashSet<>();

        Solution solution = new Solution();

        LinkedList<Intersection> potentialIntersections = new LinkedList<>();
        potentialIntersections.add(getRandomIntersection());

        while (!potentialIntersections.isEmpty()) {
            Intersection intersection1 = potentialIntersections.poll();
            solution.addIntersection(intersection1);
            intersection1.visit();

            for (Intersection intersection2 : intersections) {
                if (!intersection2.isVisited() &&
                        intersection1.isAdjacent(intersection2))
                    potentialIntersections.add(intersection2);
            }
            /*
            if (solution.isCompatible(intersection1)) {
                solution.addIntersection(intersection1);
                intersection1.visit();
                for (Intersection intersection2 : intersections) {
                    if (!intersection2.isVisited() && intersection1.isAdjacent(intersection2))
                        potentialIntersections.add(intersection2);
                }
            }*/
        }

        /*
        while (!visitedAllIntersections()) {
            Collections.shuffle(intersections);
            Intersection intersection1 = intersections.get(0);
            intersection1.visit();
            for (Intersection intersection2 : intersections) {
                if (!intersection2.isVisited() && intersection1.isCompatible(intersection2)) {
                    solution.addIntersection(intersection2);
                }
                intersection2.visit();
            }
        }
        solutions.add(solution);
        */

        return null;
    }

    private Intersection getRandomIntersection() {
        return intersections.get(0);
    }

    /*
    private void sweep(Intersection intersection) {
        for (Intersection item : intersections) {
            if (intersection.isAdjacent(item)) {

            }
        }
    }*/
}
