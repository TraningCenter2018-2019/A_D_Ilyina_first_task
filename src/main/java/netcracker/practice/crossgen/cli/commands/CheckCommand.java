package netcracker.practice.crossgen.cli.commands;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.logic.crossword.Crossword;
import netcracker.practice.crossgen.ui.GameTerminal;

public class CheckCommand extends AbstractCommand {
    public CheckCommand() {
        super("Ответы", "Показать ответы к кроссворду");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        if (!game.isPlayed()) {
            terminal.showMessage("Предупреждение",
                    "Невозможно показать ответы, так как игра не была начата!");
            return;
        }
        Crossword crossword = game.getCrossword();
        terminal.showMessage("Ответы", crossword.toString().replaceAll(".(?!$)", "$0 "));
    }
}
