package netcracker.practice.crossgen.ui;

import com.googlecode.lanterna.gui2.dialogs.DialogWindow;

import java.io.IOException;
import java.util.Map;

public interface GameTerminal extends AutoCloseable {
    void start() throws IOException;
    void close() throws IOException;
    void closeMainWindow();
    Map<String, String> getCommandDescriptions();
    void addMenuItem(String label, Runnable action);
    boolean confirm(String message);
    void fillTable(char[][] grid, boolean hideCharacters);
    Object showDialog(DialogWindow dialog);
    void showMessage(String title, String message);
    void setInfo(String text);
}
