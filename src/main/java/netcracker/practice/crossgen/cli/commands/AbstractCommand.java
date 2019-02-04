package netcracker.practice.crossgen.cli.commands;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

public abstract class AbstractCommand implements Command {
    protected final String name;
    protected final String description;

    protected AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override public String getDescription() {
        return this.description;
    }

    @Override public String getName() {
        return this.name;
    }

    @Override public abstract void execute(Game game, GameTerminal terminal);
}
