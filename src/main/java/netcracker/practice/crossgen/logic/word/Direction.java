package netcracker.practice.crossgen.logic.word;

import netcracker.practice.crossgen.logic.angle.Angle;
import netcracker.practice.crossgen.logic.angle.DiagonalAngle;
import netcracker.practice.crossgen.logic.angle.StraightAngle;

public enum Direction {
    HORIZONTAL("По горизонтали"),
    VERTICAL("По вертикали"),
    DIAGONAL("По главной диагонали"),
    ANTI_DIAGONAL("По побочной диагонали");

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
