package netcracker.practice.crossgen.game;

import netcracker.practice.crossgen.game.states.GameState;
import netcracker.practice.crossgen.game.states.ConfiguringState;
import netcracker.practice.crossgen.game.states.InitialState;
import netcracker.practice.crossgen.game.states.PlayingState;
import netcracker.practice.crossgen.logic.crossword.Crossword;
import netcracker.practice.crossgen.logic.generator.Generator;

import java.util.ArrayList;

public class CrosswordGame implements Game {
    private Configuration config;
    private GameState state;
    private ArrayList<GameObserver> observers = new ArrayList<>();

    private Generator generator;
    private Crossword crossword;

    public CrosswordGame() {
        state = new InitialState();
        debug();
    }

    private void debug() {
        config = new CrosswordConfiguration();
        ((CrosswordConfiguration) config).setHeight(12);
        ((CrosswordConfiguration) config).setWidth(12);
        try {
            //config.setClues("як: ...\nкот:...\nбаян:  ... \n кета: ...");
            ((CrosswordConfiguration) config).setConstraints(
                    "0 0\n7 7"
            );
            ((CrosswordConfiguration) config).setClues(
                "лис: Хитрый зверь.\n" +
                "усы: Поросль на лице.\n" +
                "ода: Торжественное стихотворение.\n" +
                "икра: И рыбья, и овощная.\n" +
                "зевс: Верховный бог у древних греков.\n" +
                "рада: Парламент Украины.\n" +
                "тротил: Взрывчатка.\n" +
                "щи: Суп с капустой.\n" +
                "ги: Имя Мопассана.\n" +
                "сатира: Вид художественной литературы.\n" +
                "трак: Звено гусеницы.\n" +
                "сани: Летняя забота владельца гужевого транспорта (фольк.).\n" +
                "агат: Минерал, разновидность кварца.\n" +
                "мао: Великий вождь китайского народа.\n" +
                "ока: Крупнейший приток Волги.\n" +
                "ура: \"Банзай\" по-русски.\n" +
                "воз: Колесная повозка или сани с кладью.\n" +
                "лавр: Дерево с листьями и для венка, и для борща.\n" +
                "си: Нота.\n" +
                "урал: Горный массив в России.\n" +
                "сад: Участок земли, засаженный деревьями, кустами, цветами.\n" +
                "дети: Цветы жизни.\n" +
                "кризис: Переломный момент в болезни.\n" +
                "собака: Домашнее животное.\n" +
                "али: Мусульманское имя Кассиуса Клея.\n" +
                "щит: Средство обороны от холодного оружия.\n" +
                "гана: Государство в Западной Африке.\n" +
                "сага: Древнескандинавское народное сказание.\n" +
                "рама: Она украшает картину.\n" +
                "рак: Зодиакальное созвездие.\n" +
                "ион: Электрически заряженная частица.\n" +
                "ту: Марка, тип российских самолетов.");
            state = new ConfiguringState();
            state.doAction(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Crossword generate() throws GameException {
        if (!isConfigured())
            throw new GameException("Generator hasn't been defined.");
        crossword = generator.generate();
        return crossword;
    }

    @Override
    public Crossword getCrossword() {
        return crossword;
    }

    @Override
    public Configuration getConfiguration() {
        return config;
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public boolean isConfigured() {
        return state.getClass() == ConfiguringState.class;
    }

    @Override
    public boolean isNew() {
        return state.getClass() == InitialState.class;
    }

    @Override
    public boolean isPlayed() {
        return state.getClass() == PlayingState.class;
    }

    @Override
    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public void restart() {
        config = null;
        state = new InitialState();
        observers.clear();
    }

    @Override
    public void setConfiguration(Configuration config) {
        this.config = config;
    }

    @Override
    public void setCrossword(Crossword crossword) {
        this.crossword = crossword;
    }

    @Override
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void setState(GameState state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void subscribe(GameObserver observer) {
        observers.add(observer);
    }
}
