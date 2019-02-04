package netcracker.practice.crossgen.cli.commands;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import netcracker.practice.crossgen.game.CrosswordConfiguration;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.ui.GameTerminal;

import java.util.regex.Pattern;

public class ConfigureCommand extends AbstractCommand {
    public ConfigureCommand() {
        super("Конфигурировать", "Задать размер, ограничения и слова для генерируемого кроссворда");
    }

    @Override
    public void execute(Game game, GameTerminal terminal) {
        CrosswordConfiguration config = (CrosswordConfiguration) game.getConfiguration();
        terminal.showDialog(new ActionListDialogBuilder()
                .setTitle("Конфигурировать")
                .setDescription("Выберите действие:")
                .setCloseAutomaticallyOnAction(false)
                .addAction("Задать высоту", new Runnable() {
                    @Override
                    public void run() {
                        Object result = terminal.showDialog(new TextInputDialogBuilder()
                                .setTitle("Высота сетки")
                                .setValidationPattern(Pattern.compile("[1-9][0-9]*"),
                                        "Вы не ввели положительное число")
                                .setInitialContent(String.valueOf(config.getHeight()))
                                .build());
                        if (result != null) {
                            config.setHeight(Integer.parseInt(result.toString()));
                        }
                    }
                })
                .addAction("Задать ширину", new Runnable() {
                    @Override
                    public void run() {
                        Object result = terminal.showDialog(new TextInputDialogBuilder()
                                .setTitle("Ширина сетки")
                                .setValidationPattern(Pattern.compile("[1-9][0-9]*"),
                                        "Вы не ввели положительное число")
                                .setInitialContent(String.valueOf(config.getWidth()))
                                .build());
                        if (result != null) {
                            config.setWidth(Integer.parseInt(result.toString()));
                        }
                    }
                })
                .addAction("Задать ограничения", new Runnable() {
                    @Override
                    public void run() {
                        Object result = terminal.showDialog(new TextInputDialogBuilder()
                                .setTitle("Ограничения")
                                .setDescription("Задайте координаты чёрных клеток\n" +
                                        "на поле в формате \"ряд колонка\". Каждую\n" +
                                        "пару указывайте на новой строке.")
                                .setValidationPattern(Pattern.compile("(^$|(([0-9]+ [0-9]+)(\n[0-9]+ [0-9]+)*))"),
                                        "Неверный формат ограничений")
                                .setTextBoxSize(new TerminalSize(30, 10))
                                .setInitialContent(config.getConstraints())
                                .build());
                        if (result != null) {
                            config.setConstraints(result.toString());
                        }
                    }
                })
                .addAction("Задать подсказки", new Runnable() {
                    @Override
                    public void run() {
                        Object result = terminal.showDialog(new TextInputDialogBuilder()
                                .setTitle("Подсказки")
                                .setDescription("Задайте слова и подсказки в формате \"слово: подсказка\"")
                                .setValidationPattern(Pattern.compile("(.+:.+)(\n.+:.+)*"),
                                        "Неверный формат подсказок")
                                .setTextBoxSize(new TerminalSize(30, 10))
                                .setInitialContent(config.getClues())
                                .build());
                        if (result != null) {
                            config.setClues(result.toString());
                        }
                    }
                }).build());
    }
}
