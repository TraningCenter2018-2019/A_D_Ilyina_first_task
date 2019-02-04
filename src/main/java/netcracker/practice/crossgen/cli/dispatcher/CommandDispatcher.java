package netcracker.practice.crossgen.cli.dispatcher;

import netcracker.practice.crossgen.cli.commands.Command;
import netcracker.practice.crossgen.ui.GameTerminal;

import java.util.Map;

public interface CommandDispatcher {
    void add(Command cmd);
    Map<String, String> getCommandDescriptions();
    void execute(String commandName, GameTerminal terminal);
    void setUpTerminal(GameTerminal terminal);
}
