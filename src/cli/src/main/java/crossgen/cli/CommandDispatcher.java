package crossgen.cli;

public interface CommandDispatcher {
    //boolean isCommand(int commandNumber);
    void executeCommand(int commandNumber) throws Exception;
    void addCommand(Command command);
}
