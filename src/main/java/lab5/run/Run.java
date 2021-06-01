package lab5.run;

import lab5.commands.*;
import lab5.tools.*;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        //for (String envName: System.getenv().keySet()) System.out.println(envName);
        String env = "LAB5";
        FileManager fileManager = new FileManager(env);
        Scanner scanner = new Scanner(System.in);
        FieldsAsker fieldsAsker = new FieldsAsker(scanner);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(
                new AddCommand(collectionManager, fieldsAsker),
                new AddIfMinCommand(collectionManager, fieldsAsker),
                new ClearCommand(collectionManager),
                new CountByHealthCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new MaxByHealthCommand(collectionManager),
                new PrintUniqueChapterCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new RemoveFirstCommand(collectionManager),
                new RemoveGreaterCommand(collectionManager, fieldsAsker),
                new SaveCommand(collectionManager),
                new ShowCommand(collectionManager),
                new UpdateIdCommand(collectionManager, fieldsAsker)
        );
        ConsoleManager consoleManager = new ConsoleManager(commandManager, scanner, fieldsAsker);
        consoleManager.interactiveMode();
    }
}
