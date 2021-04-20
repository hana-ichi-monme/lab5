package lab5.commands;

import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.ConsoleManager;

public class ExitCommand extends AbstractCommand{
    public ExitCommand() {
        super("exit", "ends application");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        }
        return false;
    }
}
