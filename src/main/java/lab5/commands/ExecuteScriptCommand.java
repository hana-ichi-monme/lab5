package lab5.commands;

import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.ConsoleManager;

public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand() {
        super("execute_script file_name", "read and execute script from file");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongArgumentOfCommandException();
            ConsoleManager.println("Execute script: '" + argument + "'...");
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must not be empty");
        }
        return false;
    }
}
