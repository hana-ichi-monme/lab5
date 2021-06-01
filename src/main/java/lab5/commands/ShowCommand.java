package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.tools.Shower;
import lab5.types.SpaceMarine;

import java.util.Deque;

public class ShowCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "shows all collection elements in string format");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            if (collectionManager.collectionIsEmpty())
                throw new CollectionIsEmptyException();
            Deque<SpaceMarine> collection = collectionManager.getMarineCollection();
            collection.forEach(Shower::show);
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
