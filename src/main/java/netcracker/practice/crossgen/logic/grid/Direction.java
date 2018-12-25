package netcracker.practice.crossgen.logic.grid;

public enum Direction {
    HORIZONTAL("Across"),
    VERTICAL("Down"),
    DIAGONAL("Diagonal"),
    ANTI_DIAGONAL("Anti-diagonal");

    final String name;

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Angle getAngle() {
        switch(this) {
            case HORIZONTAL: return new StraightAngle();
            case VERTICAL: return new StraightAngle();
            case DIAGONAL: return new DiagonalAngle();
            case ANTI_DIAGONAL: return new DiagonalAngle();
            default: throw new IllegalStateException("Error: " + this + " has no associated angle");
        }
    }

    public boolean isOrthogonal(Direction direction) {
        return direction == this.orthogonal();
    }

    public Direction orthogonal() {
        switch(this) {
            case HORIZONTAL: return Direction.VERTICAL;
            case VERTICAL: return Direction.HORIZONTAL;
            case DIAGONAL: return Direction.ANTI_DIAGONAL;
            case ANTI_DIAGONAL: return Direction.DIAGONAL;
            default: throw new IllegalStateException("Error: " + this + " has no opposite");
        }
    }
}
