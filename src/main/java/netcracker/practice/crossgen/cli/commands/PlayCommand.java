package netcracker.practice.crossgen.cli.commands;

import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.game.states.PlayingState;
import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;
import netcracker.practice.crossgen.ui.GameTerminal;

public class PlayCommand extends AbstractCommand {
    public PlayCommand() {
        super( "Играть", "Сыграть в один из ранее слзданных кроссвордов");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        String filename = String.valueOf(
                terminal.showDialog(new FileDialogBuilder()
                        .setTitle("Открыть файл")
                        .setDescription("Выберите кроссворд для загрузки")
                        .setActionLabel("Открыть")
                        .build())
        );
        if (!(filename == null || filename.equals("null"))) {
            try {
                CanadianCrossword crossword = new CanadianCrossword();
                crossword.getFormatter().deserialize(filename);

                terminal.fillTable(crossword.toCharMatrix(), true);
                terminal.setInfo(crossword.getInfo());

                game.setCrossword(crossword);
                game.setState(new PlayingState());
            } catch (Exception e) {
                terminal.showMessage("Ошибка", "Возникла ошибка во время загрузки файла:\n" + e.getMessage());
            }
        }
    }
}