package netcracker.practice.crossgen.logic.grid;

public enum Direction {
    HORIZONTAL("Across"),
    VERTICAL("Down"),
    DIAGONAL("Main"),
    ANTI_DIAGONAL("Anti");

    final String name;

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Direction orthogonal() {
        switch(this) {
            case HORIZONTAL: return Direction.VERTICAL;
            case VERTICAL: return Direction.HORIZONTAL;
            case DIAGONAL: return Direction.ANTI_DIAGONAL;
            case ANTI_DIAGONAL: return Direction.DIAGONAL;
            default: throw new IllegalStateException("Error: " + this + " has no opposite.");
        }
    }

    public boolean isOrthogonal(Direction direction) {
        return direction == this.orthogonal();
    }
}
