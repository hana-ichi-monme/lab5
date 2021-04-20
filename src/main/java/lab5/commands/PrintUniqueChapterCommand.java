package lab5.commands;

import lab5.exceptions.CollectionIsEmptyException;
import lab5.exceptions.WrongArgumentOfCommandException;
import lab5.tools.CollectionManager;
import lab5.tools.ConsoleManager;

public class PrintUniqueChapterCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    public PrintUniqueChapterCommand(CollectionManager collectionManager) {
        super("print_unique_chapter", "prints unique values of chapter among all elements of collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new WrongArgumentOfCommandException();
            ConsoleManager.println(collectionManager.uniqueChapters());
            return true;
        } catch (CollectionIsEmptyException e) {
            ConsoleManager.printError("Collection is empty");
        } catch (WrongArgumentOfCommandException e) {
            ConsoleManager.printError("Argument must be empty");
        }
        return false;
    }
}
