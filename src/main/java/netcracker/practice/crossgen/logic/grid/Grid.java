package netcracker.practice.crossgen.logic.grid;

public abstract class Grid {
    private int width;
    private int height;

    protected Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
