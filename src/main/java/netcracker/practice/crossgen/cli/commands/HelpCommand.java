package netcracker.practice.crossgen.cli.commands;

import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

import java.util.Map;

public class HelpCommand extends AbstractCommand {
    public HelpCommand() {
        super("Помощь", "Получить помощь по игре");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        StringBuilder sb = new StringBuilder();
        sb.append("Доступные действия:\n");
        for (Map.Entry<String, String> entry : terminal.getCommandDescriptions().entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        terminal.showMessage(this.name, sb.toString());
    }
}
