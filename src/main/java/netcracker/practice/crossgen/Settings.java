package netcracker.practice.crossgen;

public interface Settings {

    /**
     * Grid settings
     */
    char CONSTRAINED_SYMBOL = '#';
    char EMPTY_SYMBOL = '_';

    /**
     * Generator settings
     */
    int BAD_SOLUTION_GAP = 2;
    long GENERATION_TIMEOUT = 1000;

}
