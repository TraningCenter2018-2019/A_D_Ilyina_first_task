package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

public class BaseGrid implements Grid {

    private final int height;
    private final int width;
    private final Set<Coordinate> constraints = new HashSet<>();

    public BaseGrid(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public BaseGrid(int height, int width, Set<Coordinate> constraints) {
        this.height = height;
        this.width = width;
        for (Coordinate constraint : constraints) {
            if (!constraint.isWithinBounds(height, width))
                throw new IllegalArgumentException("Coordinate is not within the grid");
            this.constraints.add(constraint);
        }
    }

    @Override
    public Set<Coordinate> getConstraints() {
        return constraints;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public char getSymbol(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col > width)
            throw new IllegalArgumentException("Symbol coordinates must be inside the grid.");
        Coordinate c = new Coordinate(row, col);
        return constraints.contains(c) ? Settings.CONSTRAINED_SYMBOL : Settings.EMPTY_SYMBOL;
    }

    @Override
    public char[][] toCharMatrix() {
        return new CharGrid(this).toCharMatrix();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseGrid)) return false;

        BaseGrid grid = (BaseGrid) o;
        return height == grid.height && width == grid.width &&
                constraints.equals(grid.constraints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, constraints);
    }
}
