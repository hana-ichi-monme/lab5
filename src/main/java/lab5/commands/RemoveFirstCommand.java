package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;

public class RemoveFirstCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public RemoveFirstCommand(CollectionManager collectionManager) {
        super("remove_first", "remove first element from collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            collectionManager.removeFirst();
            ConsoleManager.println("Marine removed successfully");
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
