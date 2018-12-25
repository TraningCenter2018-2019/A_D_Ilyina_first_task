package netcracker.practice.crossgen.cli;

import java.util.TreeMap;

import netcracker.practice.crossgen.game.Game;

class CrosswordCommandDispatcher implements CommandDispatcher {
    private final TreeMap<Integer, Command> commands = new TreeMap<>();

    public CrosswordCommandDispatcher(Game game) {
        addCommand(new PlayCommand(game));
        addCommand(new ConfigureCommand(game));
        addCommand(new GetHelpCommand(game));
        addCommand(new QuitCommand(game));
        addCommand(new DeleteCommand(game));
        addCommand(new CreateCommand(game));
        addCommand(new SaveCommand(game));
        addCommand(new CheckCommand(game));
    }

    @Override
    public void executeCommand(int commandNumber) throws Exception {
        if (!commands.containsKey(commandNumber))
            throw new Exception("Command with given number not found.");
        commands.get(commandNumber).execute();
    }

    @Override
    public void addCommand(Command command) {
        commands.put(commands.lastKey() + 1, command);
    }
}
