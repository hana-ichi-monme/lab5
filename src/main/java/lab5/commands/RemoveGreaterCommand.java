package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.tools.FieldsAsker;
import lab5.types.SpaceMarine;

import java.time.LocalDateTime;

public class RemoveGreaterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private FieldsAsker fieldsAsker;
    public RemoveGreaterCommand(CollectionManager collectionManager, FieldsAsker fieldsAsker) {
        super("remove_greater", "remove all marines if they are greater than this one");
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldsAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            collectionManager.removeGreater(new SpaceMarine(
                    fieldsAsker.askName(),
                    fieldsAsker.askCoordinates(),
                    LocalDateTime.now(),
                    fieldsAsker.askHealth(),
                    fieldsAsker.askHeight(),
                    fieldsAsker.askCategory(),
                    fieldsAsker.askMeleeWeapon(),
                    fieldsAsker.askChapter()));
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        }
        return false;
    }
}
