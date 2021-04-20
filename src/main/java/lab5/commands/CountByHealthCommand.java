package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;

public class CountByHealthCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public CountByHealthCommand(CollectionManager collectionManager) {
        super("count_by_health health", "prints the count of items whose health is equal to the specified value");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            Long health = Long.parseLong(argument);
            ConsoleManager.println(collectionManager.countByHealth(health));
            return true;
        } catch (NumberFormatException e) {
            ConsoleManager.printError(" must be a number");
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
