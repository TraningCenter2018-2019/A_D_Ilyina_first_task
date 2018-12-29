package netcracker.practice.crossgen.logic.grid;

public interface MutableGrid extends Grid {

    void setSymbol(int row, int col, char symbol);
    void put(Word word);
    void setConstrainedBorders(Word word);

}
