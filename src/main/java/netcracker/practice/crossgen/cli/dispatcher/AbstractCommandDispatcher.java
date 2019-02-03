package netcracker.practice.crossgen.cli.dispatcher;

import netcracker.practice.crossgen.cli.commands.Command;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractCommandDispatcher implements CommandDispatcher {
    protected final LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    protected final Game game;

    protected AbstractCommandDispatcher(Game game) {
        this.game = Objects.requireNonNull(game);
    }

    @Override public void add(Command cmd) {
        commands.put(cmd.getName(), cmd);
    }

    @Override
    public Map<String, String> getCommandDescriptions() {
        LinkedHashMap<String, String> descriptions = new LinkedHashMap<>();
        commands.forEach((name, cmd) -> descriptions.put(name, cmd.getDescription()));
        return descriptions;
    }

    @Override public void execute(String commandName, GameTerminal terminal) {
        if (!commands.containsKey(commandName))
            throw new IllegalStateException("No such command in CommandDispatcher");
        commands.get(commandName).execute(game, terminal);
    }
}
