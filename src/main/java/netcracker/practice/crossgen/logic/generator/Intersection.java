package netcracker.practice.crossgen.logic.generator;

import crossgen.logic.grid.Word;

abstract class Match {
    protected Object obj1;
    protected Object obj2;
    protected int position1;
    protected int position2;

    public Match(Object obj1, Object obj2, int position1, int position2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.position1 = position1;
        this.position2 = position2;
    }

    public int getPosition1() {
        return position1;
    }

    public int getPosition2() {
        return position2;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (!(object instanceof Match)) return false;

        Match wordMatch = (Match) object;
        return (wordMatch.obj1.equals(this.obj1) &&
                wordMatch.obj2.equals(this.obj2) &&
                wordMatch.position1 == this.position1 &&
                wordMatch.position2 == this.position2) ||
                    (wordMatch.obj1.equals(this.obj2) &&
                    wordMatch.obj2.equals(this.obj1) &&
                    wordMatch.position1 == this.position2 &&
                    wordMatch.position2 == this.position1);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(obj1, obj2, position1, position2);
        return 31 * 17 + obj1.hashCode() + obj2.hashCode() + position1 + position2;
    }

    /*
    @Override
    public String toString() {
        return obj1.toString() + " " +
                obj2.toString() + " " + position1 + " " + position2;
    }*/
}

class WordMatch extends Match {
    public WordMatch(String word1, String word2, int position1, int position2) {
        super(word1, word2, position1, position2);
    }

    public String getWord1() {
        return (String)obj1;
    }

    public String getWord2() {
        return (String)obj2;
    }
}

class GridWordMatch extends Match {
    public GridWordMatch(Word gridWord1, Word gridWord2, int position1, int position2) {
        super(gridWord1, gridWord2, position1, position2);
    }

    public Word getGridWord1() {
        return (Word)obj1;
    }

    public Word getGridWord2() {
        return (Word)obj2;
    }

    /*
    @Override
    public String toString() {
        return ((Word)obj1).getRow() + "," + ((Word)obj1).getCol() + " " +
                ((Word)obj1).getDirection() + " " +
                ((Word)obj2).getRow() + "," + ((Word)obj2).getCol() + " " +
                ((Word)obj2).getDirection();
    }*/
}

class Intersection extends Match {
    private boolean visited = false;

    public Intersection(GridWordMatch gridWordMatch, WordMatch wordMatch, int position1, int position2) {
        super(gridWordMatch, wordMatch, position1, position2);
    }

    public GridWordMatch getGridWordMatch() {
        return (GridWordMatch)obj1;
    }

    public WordMatch getWordMatch() {
        return (WordMatch)obj2;
    }

    public void visit() {
        this.visited = true;
    }

    public void clearVisit() {
        this.visited = false;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public boolean isAdjacent(Intersection intersection) {
        GridWordMatch gridMatch1 = this.getGridWordMatch();
        GridWordMatch gridMatch2 = intersection.getGridWordMatch();
        WordMatch wordMatch1 = this.getWordMatch();
        WordMatch wordMatch2 = intersection.getWordMatch();

        if (gridMatch1.equals(gridMatch2)) return false;

        if (gridMatch1.getGridWord1().equals(gridMatch2.getGridWord1())) {
            return wordMatch1.getWord1().equals(wordMatch2.getWord1()) &&
                    position1 == intersection.position1 &&
                    !wordMatch1.getWord2().equals(wordMatch2.getWord2());
        }
        /*
        if (gridMatch1.getGridWord2().equals(gridMatch2.getGridWord1())) {
            if (wordMatch1.getWord2().equals(wordMatch2.getWord1()) &&
                    position2 == intersection.position1 &&
                    !wordMatch1.getWord1().equals(wordMatch2.getWord2()))
                return true;
        }*/

        return false;
    }
}
