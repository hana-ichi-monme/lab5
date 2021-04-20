package lab5.commands;

import lab5.exceptions.IncorrectInputInScriptException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.tools.FieldsAsker;
import lab5.types.SpaceMarine;

import java.time.LocalDateTime;

public class AddCommand extends AbstractCommand {
    CollectionManager collectionManager;
    FieldsAsker fieldsAsker;
    public AddCommand(CollectionManager collectionManager, FieldsAsker fieldsAsker) {
        super("add {element}", "adds an new item to the collection");
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldsAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            collectionManager.addToCollection(new SpaceMarine(
                    fieldsAsker.askName(),
                    fieldsAsker.askCoordinates(),
                    LocalDateTime.now(),
                    fieldsAsker.askHealth(),
                    fieldsAsker.askHeight(),
                    fieldsAsker.askCategory(),
                    fieldsAsker.askMeleeWeapon(),
                    fieldsAsker.askChapter()));
            ConsoleManager.println("Marine added successfully");
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        } catch (IncorrectInputInScriptException e) {
            ConsoleManager.printError("Incorrect input in script");
        }
        return false;
    }
}
