package netcracker.practice.crossgen.logic.grid;

import java.util.Objects;

public abstract class BaseGrid implements Grid {

    private final int height;
    private final int width;
    //private final List<int[]> constraints;

    protected BaseGrid(int height, int width) {
        this.height = height;
        this.width = width;
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
    public abstract String stringify();

    @Override
    public abstract char getSymbol(int row, int col);

    @Override
    public abstract void setSymbol(int row, int col, char symbol);

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseGrid)) return false;

        BaseGrid grid = (BaseGrid) o;
        return height == grid.height && width == grid.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    @Override
    public String toString() {
        return "height : " + height + ", width: " + width;
    }

}
