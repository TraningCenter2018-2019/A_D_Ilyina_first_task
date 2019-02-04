package netcracker.practice.crossgen.game.states;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.game.GameException;

public interface GameState {
    void doAction(Game game) throws GameException;
}
