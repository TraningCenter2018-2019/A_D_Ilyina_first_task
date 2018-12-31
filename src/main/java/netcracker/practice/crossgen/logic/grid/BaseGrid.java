package netcracker.practice.crossgen.logic.grid;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

public abstract class BaseGrid implements Grid {

    private final int height;
    private final int width;
    private final Set<Coordinate> constraints = new HashSet<>();

    protected BaseGrid(int height, int width) {
        this.height = height;
        this.width = width;
    }

    protected BaseGrid(int height, int width, Set<Coordinate> constraints) {
        this.height = height;
        this.width = width;
        constraints.addAll(constraints);
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
