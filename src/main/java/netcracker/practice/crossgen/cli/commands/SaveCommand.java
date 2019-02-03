package netcracker.practice.crossgen.cli.commands;

import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.logic.crossword.Crossword;
import netcracker.practice.crossgen.ui.GameTerminal;

import java.io.IOException;

public class SaveCommand extends AbstractCommand {
    public SaveCommand() {
        super("Сохранить", "Сохранить кроссворд, указав директорий");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        Crossword crossword = game.getCrossword();
        if (crossword == null || game.isPlayed()) {
            terminal.showMessage("Ошибка", "Необходимо сгенерировать кроссворд, прежде чем его сохранить.");
            return;
        }

        String filename = String.valueOf(
                terminal.showDialog(new FileDialogBuilder()
                    .setTitle("Сохранить файл")
                    .setDescription("Укажите название нового файла")
                    .setActionLabel("Сохранить")
                    .build())
        );

        try {
            crossword.getFormatter().serialize(filename);
            terminal.showMessage("Сохранение", "Кроссворд успешно сохранён.");
        } catch (IOException e) {
            terminal.showMessage("", "Произошла ошибка во врем записи файла.");
        }
    }
}
