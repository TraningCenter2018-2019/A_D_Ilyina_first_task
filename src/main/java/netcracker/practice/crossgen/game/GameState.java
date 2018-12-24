package netcracker.practice.crossgen.game;

public interface GameState {
    void doAction(Game game);
}

class InitialState implements GameState {
    @Override
    public void doAction(Game game) {
        // TODO : implement GameState logic
    }
}

class PlayingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class PickingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class ConfiguringState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class DeletingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class CreatingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class SavingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class CheckingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}

class QuittingState implements GameState {
    @Override
    public void doAction(Game game) {

    }
}
