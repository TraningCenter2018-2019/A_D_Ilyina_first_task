package netcracker.practice.crossgen;

import netcracker.practice.crossgen.cli.dispatcher.CrosswordCommandDispatcher;
import netcracker.practice.crossgen.game.CrosswordGame;
import netcracker.practice.crossgen.ui.CrosswordTerminal;
import netcracker.practice.crossgen.ui.GameTerminal;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        CrosswordGame game = new CrosswordGame();
        CrosswordCommandDispatcher dispatcher = new CrosswordCommandDispatcher(game);
        try (GameTerminal gt = new CrosswordTerminal(game, dispatcher)) {
            gt.start();
        } catch (IOException e) {
            log.error("An error occurred while starting the terminal", e);
        }
    }
}
