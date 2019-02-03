package netcracker.practice.crossgen.cli.commands;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

public class QuitCommand extends AbstractCommand {
    public QuitCommand() {
        super("Выйти", "Выйти из игры");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        if (terminal.confirm("Вы действительно хотите выйти?")) {
            terminal.closeMainWindow();
            game.restart();
        }
    }
}
