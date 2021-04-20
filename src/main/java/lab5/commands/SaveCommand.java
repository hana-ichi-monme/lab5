package lab5.commands;

import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;

public class SaveCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public SaveCommand(CollectionManager collectionManager) {
        super("save", "saves collection in file");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            collectionManager.save();
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        }
        return false;
    }
}
