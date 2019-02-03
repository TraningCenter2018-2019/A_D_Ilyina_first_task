package netcracker.practice.crossgen.cli.commands;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

public interface Command {
    void execute(Game game, GameTerminal terminal);
    String getDescription();
    String getName();
}
