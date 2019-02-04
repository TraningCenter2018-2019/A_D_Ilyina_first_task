package netcracker.practice.crossgen.logic.crossword;

import netcracker.practice.crossgen.logic.grid.Grid;

import java.io.IOException;

public abstract class Crossword implements Grid {
    /*protected final BaseGrid grid;

    protected Crossword(BaseGrid grid) {
        this.grid = grid;
    }

    protected Crossword(int height, int width) {
        this(new BaseGrid(height, width));
    }

    protected Crossword(int height, int width, Set<Coordinate> constraints) {
        this(new BaseGrid(height, width, constraints));
    }*/

    public abstract String getInfo();

    public abstract Formatter getFormatter();

    protected abstract class CrosswordFormatter<T extends CrosswordFormatter<T>> implements Formatter {
        @Override
        public abstract void deserialize(String filename) throws IOException;

        @Override
        public abstract void serialize(String filename) throws IOException;
    }
}
