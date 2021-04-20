package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.IncorrectInputInScriptException;
import lab5.exceptions.MarineNotFoundException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;
import lab5.tools.FieldsAsker;
import lab5.types.SpaceMarine;

public class UpdateIdCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private FieldsAsker fieldsAsker;
    public UpdateIdCommand(CollectionManager collectionManager, FieldsAsker fieldsAsker) {
        super("update id {element}", "update collection element by id");
        this.collectionManager = collectionManager;
        this.fieldsAsker = fieldsAsker;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            if (collectionManager.collectionIsEmpty())
                throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            collectionManager.update(
                    collectionManager.getById(id),
                    fieldsAsker.askName(),
                    fieldsAsker.askCoordinates(),
                    fieldsAsker.askHealth(),
                    fieldsAsker.askHeight(),
                    fieldsAsker.askCategory(),
                    fieldsAsker.askMeleeWeapon(),
                    fieldsAsker.askChapter());
            ConsoleManager.println("Marine updated successfully");

        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must not be empty");
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        } catch (MarineNotFoundException e) {
            ConsoleManager.printError("Marine with this id not found");
        } catch (IncorrectInputInScriptException e) {
            ConsoleManager.printError("Incorrect input in script");
        }
        return false;
    }
}
