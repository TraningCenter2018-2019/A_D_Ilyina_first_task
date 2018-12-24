package netcracker.practice.crossgen.cli;

import netcracker.practice.crossgen.game.Game;

public abstract class Command {
    protected Game game;
    private String name;
    private String description;

    public Command(Game game, String name, String description) {
        this.game = game;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract void execute();
}

class PlayCommand extends Command {
    public PlayCommand(Game game) {
        // TODO : add command descriptions
        super(game, "Играть", "");
    }

    public void execute() {
        game.play();
    }
}

class ConfigureCommand extends Command {
    public ConfigureCommand(Game game) {
        super(game, "Конфигурировать", "");
    }

    public void execute() {
        game.configure();
    }
}

class GetHelpCommand extends Command {
    public GetHelpCommand(Game game) {
        super(game, "Помощь", "");
    }

    public void execute() {
        game.getHelp();
    }
}

class QuitCommand extends Command {
    public QuitCommand(Game game) {
        super(game, "Выйти", "");
    }

    public void execute() {
        game.quit();
    }
}

class DeleteCommand extends Command {
    public DeleteCommand(Game game) {
        super(game, "Удалить", "");
    }

    public void execute() {
        game.delete();
    }
}

class CreateCommand extends Command {
    public CreateCommand(Game game) {
        super(game, "Создать", "");
    }

    public void execute() {
        game.create();
    }
}

class SaveCommand extends Command {
    public SaveCommand(Game game) {
        super(game, "Сохранить", "");
    }

    public void execute() {
        game.save();
    }
}

class CheckCommand extends Command {
    public CheckCommand(Game game) {
        super(game, "Проверить", "");
    }

    public void execute() {
        game.check();
    }
}
