package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.logic.word.Word;

public interface MutableGrid extends Grid {

    void setSymbol(int row, int col, char symbol);
    void put(Word word);
    void setConstrainedBorders(Word word);

}
