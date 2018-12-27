package netcracker.practice.crossgen;

public interface Settings {

    /**
     * Grid settings
     */
    char EMPTY_SYMBOL = '_';
    char PROHIBITED_SYMBOL = '#';

    /**
     * Generator settings
     */
    int BAD_SOLUTION_GAP = 2;
    long GENERATION_TIMEOUT = 1000;

}
