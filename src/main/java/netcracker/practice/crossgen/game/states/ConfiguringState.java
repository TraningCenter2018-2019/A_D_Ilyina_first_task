package netcracker.practice.crossgen.game.states;

import netcracker.practice.crossgen.game.CrosswordConfiguration;
import netcracker.practice.crossgen.game.Game;
import netcracker.practice.crossgen.game.GameException;
import netcracker.practice.crossgen.logic.angle.AnglePicker;
import netcracker.practice.crossgen.logic.generator.CanadianCrosswordGenerator;
import netcracker.practice.crossgen.logic.grid.BaseGrid;

public class ConfiguringState implements GameState {
    @Override
    public void doAction(Game game) throws GameException {
        CrosswordConfiguration config = (CrosswordConfiguration) game.getConfiguration();
        try {
            if (!game.isConfigured())
                throw new GameException("Кроссворд не был сконфигурирован.");
            BaseGrid grid = new BaseGrid(config.getHeight(), config.getWidth(),
                    config.parseConstraints());
            game.setGenerator(new CanadianCrosswordGenerator(
                    new AnglePicker().getAngle(config.getAngle()),
                    grid,
                    config.parseClues()));
        } catch (Exception e) {
            game.setState(new InitialState());
            throw new GameException(e.getMessage());
        }
    }
}
