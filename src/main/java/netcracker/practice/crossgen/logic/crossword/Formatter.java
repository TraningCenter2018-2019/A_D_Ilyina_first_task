package netcracker.practice.crossgen.logic.crossword;

import java.io.IOException;

public interface Formatter {
    void deserialize(String filename) throws IOException;
    void serialize(String filename) throws IOException;
}
