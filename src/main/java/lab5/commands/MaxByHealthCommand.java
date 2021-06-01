package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.tools.Shower;

public class MaxByHealthCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public MaxByHealthCommand(CollectionManager collectionManager) {
        super("max_by_health", "prints space marine with max health value");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            if (collectionManager.collectionIsEmpty())
                throw new CollectionIsEmptyException();
            Shower.show(collectionManager.maxByHealth());
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
