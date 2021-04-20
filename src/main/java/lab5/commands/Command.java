package lab5.commands;

public interface Command {

    /**
     * @return Command name
     */
    String getName();

    /**
     * @return Info about command
     */
    String getInfo();

    /**
     * Execute command
     * @param argument Command argument
     * @return Execution status
     */
    boolean execute(String argument);
}