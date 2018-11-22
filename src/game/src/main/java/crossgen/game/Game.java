package crossgen.game;

public interface Game {
    void play();
    void configure();
    void getHelp();
    void quit();
    void delete();
    void create();
    void save();
    void check();
    void handleInput(String input);
}
