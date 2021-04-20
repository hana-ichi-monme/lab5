package lab5.commands;

import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;

import java.time.LocalDateTime;

public class InfoCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "displays information about collection");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String initString;
            if (lastInitTime == null)
                initString = "initialization has not yet occurred";
            else initString = lastInitTime.toLocalDate() + " " + lastInitTime.toLocalTime();
            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String saveString;
            if (lastSaveTime == null)
                saveString = "save has not yet occurred";
            else saveString = lastSaveTime.toLocalDate() + " " + lastSaveTime.toLocalTime();

            ConsoleManager.println("Information about collection:");
            ConsoleManager.println("Type: " + collectionManager.collectionType());
            ConsoleManager.println("Last initialisation time: " + initString);
            ConsoleManager.println("Last save time: " + saveString);
            ConsoleManager.println("Size of collection: " + collectionManager.collectionSize());
            return true;
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        }
        return false;
    }
}
