package netcracker.practice.crossgen.logic.generator;

import java.util.HashSet;

class Solution {
    private HashSet<Intersection> intersections = new HashSet<>();
    private int score = 0;

    public Solution() { }

    public void addIntersection(Intersection intersection) {
        intersections.add(intersection);
        score++;
    }

    public int getScore() {
        return this.score;
    }
    /*
    public boolean isCompatible(Intersection intersection) {
        for (Intersection placedIntersection : intersections) {

        }
    }*/
}
