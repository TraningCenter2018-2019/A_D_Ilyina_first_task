package netcracker.practice.crossgen.cli.dispatcher;

import netcracker.practice.crossgen.cli.commands.*;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

public class CrosswordCommandDispatcher extends AbstractCommandDispatcher {
    public CrosswordCommandDispatcher(Game game) {
        super(game);
        add(new PlayCommand());
        add(new CheckCommand());
        add(new ConfigureCommand());
        add(new GenerateCommand());
        add(new SaveCommand());
        add(new HelpCommand());
        add(new QuitCommand());
    }

    @Override
    public void setUpTerminal(GameTerminal terminal) {
        commands.forEach((name, cmd) -> terminal.addMenuItem(name, () -> cmd.execute(game, terminal)));
        terminal.setInfo("Выберите действие \nиз меню слева");
    }
}
