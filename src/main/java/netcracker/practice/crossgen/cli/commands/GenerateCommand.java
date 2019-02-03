package netcracker.practice.crossgen.cli.commands;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.game.GameException;
import netcracker.practice.crossgen.game.states.ConfiguringState;
import netcracker.practice.crossgen.logic.crossword.Crossword;
import netcracker.practice.crossgen.ui.GameTerminal;

public class GenerateCommand extends AbstractCommand {
    public GenerateCommand() {
        super("Сгенерировать", "Сгенерировать кроссворд из заданных слов");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        try {
            if (game.isNew())
                throw new GameException("Кроссворд не был сконфигурирован.");

            if (!game.isConfigured()) {
                if (!terminal.confirm("Вы действительно хотите прекратить текущее действие?"))
                    return;
                game.setState(new ConfiguringState());
            }
            game.getState().doAction(game);

            Crossword crossword = game.generate();
            if (crossword == null)
                throw new GameException("Не удалось сгенерировать кроссворд с заданными параметрами");

            terminal.fillTable(crossword.toCharMatrix(), false);
            terminal.setInfo(crossword.getInfo());
        } catch (GameException e) {
            terminal.showMessage("Ошибка", e.getMessage());
        }
    }
}
