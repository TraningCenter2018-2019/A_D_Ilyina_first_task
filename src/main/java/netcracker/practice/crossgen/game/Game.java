package netcracker.practice.crossgen.game;

import netcracker.practice.crossgen.game.states.GameState;
import netcracker.practice.crossgen.logic.crossword.Crossword;
import netcracker.practice.crossgen.logic.generator.Generator;

public interface Game {
    Crossword generate() throws GameException;
    Configuration getConfiguration();
    Crossword getCrossword();
    GameState getState();
    boolean isConfigured();
    boolean isNew();
    boolean isPlayed();
    void notifyObservers();
    void restart();
    void setConfiguration(Configuration config);
    void setCrossword(Crossword crossword);
    void setGenerator(Generator generator);
    void setState(GameState state);
    void subscribe(GameObserver observer);
}
