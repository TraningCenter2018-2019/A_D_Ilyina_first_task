package crossgen.logic.grid;

import java.util.List;
import java.util.ArrayList;

public interface Angle {
    List<Word> findGridWords(Grid grid);
}

class Straight implements Angle {
    @Override
    public List<Word> findGridWords(Grid grid) {
        List<Word> gridWords = new ArrayList<>();

        for (int i = 0; i < grid.getHeight(); i++) {
            gridWords.add(new GridWord(i, 0,
                    Direction.HORIZONTAL, grid.getWidth()));
        }

        for (int j = 0; j < grid.getWidth(); j++) {
            gridWords.add(new GridWord(0, j,
                    Direction.VERTICAL, grid.getHeight()));
        }

        return gridWords;
    }
}

class Diagonal implements Angle {
    @Override
    public List<Word> findGridWords(Grid grid) {
        List<Word> gridWords = new ArrayList<>();

        // TODO : implement me!

        return gridWords;
    }
}
