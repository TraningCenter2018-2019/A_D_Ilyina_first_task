package netcracker.practice.crossgen.logic.generator;

class Intersection {

    private final String intersectingWord;
    private final int position1;
    private final int position2;

    public Intersection(String intersectingWord, int position1, int position2) {
        this.intersectingWord = intersectingWord;
        this.position1 = position1;
        this.position2 = position2;
    }

    public String getIntersectingWord() {
        return intersectingWord;
    }

    public int getPosition1() {
        return position1;
    }

    public int getPosition2() {
        return position2;
    }

}
