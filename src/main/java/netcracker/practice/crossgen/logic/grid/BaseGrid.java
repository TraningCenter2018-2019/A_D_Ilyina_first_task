package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;

import java.util.Objects;
import java.util.Set;

public class BaseGrid implements Grid {

    private final int height;
    private final int width;
    private final Set<Coordinate> constraints;

    protected BaseGrid(int height, int width) {
        this.height = height;
        this.width = width;
        this.constraints = null;
    }

    protected BaseGrid(int height, int width, Set<Coordinate> constraints) {
        this.height = height;
        this.width = width;
        this.constraints = constraints;
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
        return constraints.contains(new Coordinate(row, col)) ?
                Settings.CONSTRAINED_SYMBOL :
                Settings.EMPTY_SYMBOL;
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

    @Override
    public String toString() {
        return (new CharGrid(this)).toString();
    }

}
