package netcracker.practice.crossgen.ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.screen.VirtualScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import netcracker.practice.crossgen.cli.dispatcher.CommandDispatcher;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.game.GameObserver;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class CrosswordTerminal extends GameObserver implements GameTerminal {
    private VirtualScreen screen;
    private boolean on = false;
    private WindowBasedTextGUI gui;
    private MainWindow mainWindow;
    private CommandDispatcher dispatcher;

    public CrosswordTerminal(Game game, CommandDispatcher dispatcher) throws IOException {
        super(game);
        screen = new VirtualScreen(new DefaultTerminalFactory().createScreen());
        this.dispatcher = Objects.requireNonNull(dispatcher);
        gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.CYAN));
        mainWindow = new MainWindow("Crossgen v1.0");
    }

    @Override
    public void close() throws IOException {
        if (screen != null) {
            screen.close();
        }
    }

    @Override
    public Map<String, String> getCommandDescriptions() {
        return dispatcher.getCommandDescriptions();
    }

    @Override
    public void closeMainWindow() {
        if (!on) throw new IllegalStateException("The GameTerminal must be started to perform this action.");
        on = false;
        mainWindow.close();
    }

    @Override
    public void start() throws IOException {
        if (on) throw new AssertionError("The terminal is already running!");
        on = true;
        screen.startScreen();
        drawUi();
        gui.addWindowAndWait(mainWindow);
    }

    @Override
    public boolean confirm(String message) {
        if (!on) throw new IllegalStateException("The GameTerminal must be started to perform this action.");
        MessageDialogButton result = new MessageDialogBuilder()
                .setTitle("Подтверждение")
                .setText(message)
                .addButton(MessageDialogButton.Yes)
                .addButton(MessageDialogButton.No)
                .build()
                .showDialog(gui);
        return result == MessageDialogButton.Yes;
    }

    private void drawUi() {
        if (!on) throw new IllegalStateException("The GameTerminal must be started to perform this action.");
        dispatcher.setUpTerminal(this);
    }

    @Override
    public Object showDialog(DialogWindow dialog) {
        if (!on) throw new IllegalStateException("The GameTerminal must be started to perform this action.");
        return dialog.showDialog(gui);
    }

    @Override
    public void showMessage(String title, String message) {
        if (!on) throw new IllegalStateException("The GameTerminal must be start()-ed to perform this action");
        new MessageDialogBuilder()
                .setTitle(title)
                .setText(message)
                .addButton(MessageDialogButton.OK)
                .build()
                .showDialog(gui);
    }

    @Override
    public void addMenuItem(String label, Runnable action) {
        if (!on) throw new IllegalStateException("The GameTerminal must be start()-ed to perform this action");
        mainWindow.addMenuItem(label, action);
    }

    @Override
    public void fillTable(char[][] grid, boolean hideCharacters) {
        if (!on) throw new IllegalStateException("The GameTerminal must be started to perform this action.");
        mainWindow.fillTable(grid, hideCharacters);
    }

    @Override
    public void setInfo(String text) {
        if (!on) throw new IllegalStateException("The GameTerminal must be started to perform this action.");
        mainWindow.setInfo(text);
    }

    @Override
    public void update() {
        if (game.isPlayed()) {
            mainWindow.listenToInput(true);
        } else {
            mainWindow.listenToInput(false);
        }
    }
}
