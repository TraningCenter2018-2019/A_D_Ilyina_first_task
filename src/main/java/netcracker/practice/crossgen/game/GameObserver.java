package netcracker.practice.crossgen.game;

import java.util.Objects;

public abstract class GameObserver {
    protected Game game;

    protected GameObserver(Game game) {
        Objects.requireNonNull(this.game = game);
        this.game.subscribe(this);
    }

    public abstract void update();
}
