package netcracker.practice.crossgen.logic.grid;

public interface Grid {

    int getHeight();
    int getWidth();
    char getSymbol(int row, int col);
    void setSymbol(int row, int col, char symbol);
    String stringify();

}
