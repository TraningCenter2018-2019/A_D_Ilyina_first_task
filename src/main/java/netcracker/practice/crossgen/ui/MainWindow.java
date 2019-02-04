package netcracker.practice.crossgen.ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import netcracker.practice.crossgen.Settings;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainWindow extends BasicWindow {
    private ActionListBox menu;
    private Table<String> table;
    private TextBox infoBox;
    private Panel contentPanel;
    private TableListener tableListener;

    public MainWindow(String title) {
        super(title);
        init();
    }

    public MainWindow() {
        super();
        init();
    }

    private void init() {
        setHints(Arrays.asList(Window.Hint.CENTERED, Hint.FIT_TERMINAL_WINDOW));

        Panel mainPanel = new Panel(new LinearLayout(Direction.HORIZONTAL));

        /* Set menu */
        menu = new ActionListBox();
        mainPanel.addComponent(menu.withBorder(Borders.singleLine()));

        /* Set content area */
        contentPanel = new Panel(new LinearLayout(Direction.VERTICAL))
                .setPreferredSize(new TerminalSize(40, 20));
        contentPanel.previousFocus(menu);
        contentPanel.nextFocus(infoBox);
        mainPanel.addComponent(contentPanel.withBorder(Borders.singleLine()));

        /* Set information area */
        infoBox = new TextBox(new TerminalSize(30,20))
                .setReadOnly(true);
        infoBox.setHorizontalFocusSwitching(true);
        infoBox.setVerticalFocusSwitching(false);
        mainPanel.addComponent(infoBox.withBorder(Borders.singleLine()));

        mainPanel.nextFocus(menu);
        setComponent(mainPanel);
    }

    public void addMenuItem(String label, Runnable action) {
        menu.addItem(label, action);
    }

    public void setInfo(String text) {
        infoBox.setText(text);
    }

    public void fillTable(char[][] grid, boolean hideCharacters) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        String[] columns = new String[colCount + 1];
        columns[0] = "";
        for (int i = 1; i <= colCount; i++) {
            columns[i] = String.valueOf(i);
        }
        table = new Table<>(columns);
        table.setCellSelection(true);
        for (int i = 0; i < rowCount; i++) {
            String[] row = new String[colCount + 1];
            row[0] = String.valueOf(i + 1);
            for (int j = 1; j <= colCount; j++) {
                char symbol = grid[i][j - 1];
                if (symbol == Settings.EMPTY_SYMBOL) {
                    symbol = Settings.CONSTRAINED_SYMBOL;
                }
                if (hideCharacters && symbol != Settings.CONSTRAINED_SYMBOL && symbol != Settings.EMPTY_SYMBOL) {
                    symbol = Settings.EMPTY_SYMBOL;
                }
                row[j] = String.valueOf(symbol);
            }
            table.getTableModel().addRow(row);
        }
        contentPanel.removeAllComponents();
        contentPanel.addComponent(table);
    }

    public void listenToInput(boolean listen) {
        if (listen) {
            if (tableListener == null)
                tableListener = new TableListener();
            addWindowListener(tableListener);
        } else {
            removeWindowListener(tableListener);
        }
    }

    private class TableListener implements WindowListener {
        @Override
        public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {

        }

        @Override
        public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {

        }

        @Override
        public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
            if (table == null || !table.isFocused())
                return;

            int col = table.getSelectedColumn();
            if (col > 0) {
                int row = table.getSelectedRow();
                String symbol = table.getTableModel().getCell(col, row);
                if (!symbol.equals(String.valueOf(Settings.CONSTRAINED_SYMBOL))) {
                    if (keyStroke.getKeyType() == KeyType.Backspace) {
                        table.getTableModel().setCell(col, row, String.valueOf(Settings.EMPTY_SYMBOL));
                    } else if (keyStroke.getKeyType() == KeyType.Character) {
                        table.getTableModel().setCell(col, row, String.valueOf(keyStroke.getCharacter()));
                    }
                }
            }
        }

        @Override
        public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {

        }
    }
}
