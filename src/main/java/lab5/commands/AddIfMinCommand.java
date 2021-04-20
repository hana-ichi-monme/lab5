package lab5.commands;

import lab5.exceptions.IncorrectInputInScriptException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.tools.FieldsAsker;
import lab5.types.SpaceMarine;

import java.time.LocalDateTime;

public class AddIfMinCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private FieldsAsker fieldsAsker;
    public AddIfMinCommand(CollectionManager collectionManager, FieldsAsker fieldsAsker) {
        super("add_if_min {element}", "adds a new item if its value is less than the smallest one");
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldsAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            SpaceMarine spaceMarine = new SpaceMarine(
                    fieldsAsker.askName(),
                    fieldsAsker.askCoordinates(),
                    LocalDateTime.now(),
                    fieldsAsker.askHealth(),
                    fieldsAsker.askHeight(),
                    fieldsAsker.askCategory(),
                    fieldsAsker.askMeleeWeapon(),
                    fieldsAsker.askChapter());
            if (collectionManager.collectionIsEmpty() || spaceMarine.compareTo(collectionManager.getFirst()) < 0) {
                collectionManager.addToCollection(spaceMarine);
                ConsoleManager.println("Marine added successfully");
                return true;
            }
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (IncorrectInputInScriptException e) {
            ConsoleManager.printError("Incorrect input in script");
        }
        return false;
    }
}
