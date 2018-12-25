package netcracker.practice.crossgen.logic.generator;

import java.util.*;

import netcracker.practice.crossgen.Settings;
import netcracker.practice.crossgen.logic.crossword.Clue;
import netcracker.practice.crossgen.logic.grid.Angle;
import netcracker.practice.crossgen.logic.grid.Grid;
import netcracker.practice.crossgen.logic.grid.Word;

class CanadianCrosswordGenerator implements Generator {

    private final Angle angle;
    private final Map<String, String> clues;
    private final Grid grid;

    private Map<Word, Map<Word, Integer>> gridIntersections = new HashMap<>();
    private final Map<String, List<Intersection>> wordIntersections = new HashMap<>();

    private final List<Solution> bestSolutions = new ArrayList<>();


    public CanadianCrosswordGenerator(Angle angle, Grid grid, Map<String, String> clues) {
        this.angle = angle;
        this.clues = clues;
        this.grid = grid;

        //computeGridIntersections();
        computeWordIntersections();
    }


    private void computeGridIntersections() {
        gridIntersections = angle.findGridWordIntersections(grid);
    }


    private void computeWordIntersections() {
        for (String word1 : clues.keySet()) {
            List<Intersection> intersections = new ArrayList<>();
            for (String word2 : clues.keySet())
                if (!word1.equals(word2))
                    for (int i = 0; i < word1.length(); i++)
                        for (int j = 0; j < word2.length(); j++)
                            if (word1.charAt(i) == word2.charAt(j))
                                intersections.add(new Intersection(word2, i, j));
            wordIntersections.put(word1, intersections);
        }
    }


    @Override
    public Grid generate() {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < Settings.MAX_TIME) {
            Solution newSolution = generateSolution();
            if (newSolution.score() > 1)
                if (bestSolutions.isEmpty())
                    bestSolutions.add(newSolution);
                else {
                    int bestScore = bestSolutions.get(0).score();
                    if (newSolution.score() == bestScore) {
                        bestSolutions.add(newSolution);
                    }
                    else if (newSolution.score() > bestScore) {
                        bestSolutions.clear();
                        bestSolutions.add(newSolution);
                    }
                }
        }

        if (bestSolutions.isEmpty())
            return null;

        return pickRandomSolution().toCrossword();
    }


    private Solution generateSolution() {
        Solution solution = new Solution(grid);
        LinkedList<Clue> potentialClues = new LinkedList<>();
        potentialClues.add(pickRandomFirstClue());

        while(!potentialClues.isEmpty()) {
            Collections.shuffle(potentialClues);
            Clue nextClue = potentialClues.pop();

            if (solution.conflicts(nextClue))
                continue;

            solution.add(nextClue);

            for (Intersection intersection : wordIntersections.get(nextClue.getWord())) {
                int row = angle.getIntersectingWordRow(nextClue,
                        intersection.getPosition1(), intersection.getPosition2());
                int col = angle.getIntersectingWordCol(nextClue,
                        intersection.getPosition1(), intersection.getPosition2());
                Clue newPotentialClue = new Clue(row, col, nextClue.getDirection().orthogonal(),
                        intersection.getIntersectingWord(), clues.get(intersection.getIntersectingWord()));

                if (!solution.contains(newPotentialClue) && solution.fitsWithinBounds(newPotentialClue))
                    potentialClues.add(newPotentialClue);
            }
        }

        return solution;
    }


    private Clue pickRandomFirstClue() {
        ArrayList<Map.Entry<String, String>> wordList = new ArrayList<>(clues.entrySet());
        Collections.shuffle(wordList);
        Map.Entry<String, String> firstEntry = wordList.get(0);

        Clue firstClue = (Clue) angle.getRandomWord(grid, firstEntry.getKey().length());
        firstClue.setWord(firstEntry.getKey());
        firstClue.setClue(firstEntry.getValue());

        return firstClue;
    }


    private Solution pickRandomSolution() {
        Collections.shuffle(bestSolutions);
        return bestSolutions.get(0);
    }

}
