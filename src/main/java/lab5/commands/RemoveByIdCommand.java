package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.MarineNotFoundException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.types.SpaceMarine;

public class RemoveByIdCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id id", "removes marine by id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            if (collectionManager.collectionIsEmpty())
                throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            SpaceMarine marine = collectionManager.getById(id);
            collectionManager.removeFromCollection(marine);
            ConsoleManager.println("Marine removed successfully");
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must not be empty");
        } catch (MarineNotFoundException e) {
            ConsoleManager.printError("Marine with this id not found");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        } catch (NumberFormatException e) {
            ConsoleManager.printError(" must be a number");
        }
        return false;
    }
}
