package lab5.tools;

import lab5.exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class ConsoleManager {
    private FieldsAsker fieldsAsker;
    private Scanner scanner;
    private CommandManager commandManager;
    private Set<String> scriptSet = new HashSet<>();


    public ConsoleManager(CommandManager commandManager, Scanner scanner, FieldsAsker fieldsAsker) {
        this.commandManager = commandManager;
        this.fieldsAsker = fieldsAsker;
        this.scanner = scanner;
    }

    /**
     * Mode for catching commands from user input.
     */
    public void interactiveMode() {
        String[] userCommand;
        boolean[] commandStatus;
        try {
            do {
                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = runCommand(userCommand);
            } while (!commandStatus[1]);
        } catch (NoSuchElementException exception) {
            printError("No user input found!");
        }
    }

    public boolean[] scriptMode(String argument) {
        String[] userCommand;
        boolean[] commandStatus;
        scriptSet.add(argument);
        try {
            Scanner scriptScanner = new Scanner(new File(argument));
            if (!scriptScanner.hasNext())
                throw new NoSuchElementException();
            Scanner oldScanner = fieldsAsker.getUserScanner();
            fieldsAsker.setUserScanner(scriptScanner);
            fieldsAsker.setMode("file");
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                ConsoleManager.println(String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script") && (scriptSet.contains(userCommand[1]))) {
                    throw new ScriptRecursionException();
                }
                commandStatus = runCommand(userCommand);
            } while (commandStatus[0] && scriptScanner.hasNextLine());
            fieldsAsker.setUserScanner(oldScanner);
            fieldsAsker.setMode("interactive");
            if (!commandStatus[0] && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                ConsoleManager.printError("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            ConsoleManager.printError("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            ConsoleManager.printError("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            ConsoleManager.printError("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            ConsoleManager.printError("Непредвиденная ошибка!");
            System.exit(0);
        }  finally {
            scriptSet.remove(argument);
        }
        return new boolean[]{false, false};
    }

    public boolean[] runCommand(String[] userCommand) {
        boolean isOk = false;
        boolean isExit = false;
        switch (userCommand[0]) {
            case "add":
                if (commandManager.add(userCommand[1]))
                    isOk = true;
                break;
            case "add_if_min":
                if (commandManager.addIfMin(userCommand[1]))
                    isOk = true;
                break;
            case "clear":
                if (commandManager.clear(userCommand[1]))
                    isOk = true;
                break;
            case "count_by_health":
                if (commandManager.countByHealth(userCommand[1]))
                    isOk = true;
                break;
            case "execute_script":
                if (commandManager.executeScript(userCommand[1])) {
                    isOk = true;
                    scriptMode(userCommand[1]);
                }
            case "exit":
                if (commandManager.exit(userCommand[1])) {
                    isExit = true;
                    isOk = true;
                }
            case "help":
                if (commandManager.help(userCommand[1]))
                    isOk = true;
                break;
            case "info":
                if (commandManager.info(userCommand[1]))
                    isOk = true;
                break;
            case "max_by_health":
                if (commandManager.maxByHealth(userCommand[1]))
                    isOk = true;
                break;
            case "print_unique_chapter":
                if (commandManager.printUniqueChapter(userCommand[1]))
                    isOk = true;
                break;
            case "remove_by_id":
                if (commandManager.removeById(userCommand[1]))
                    isOk = true;
                break;
            case "remove_first":
                if (commandManager.removeFirst(userCommand[1]))
                    isOk = true;
                break;
            case "remove_greater":
                if (commandManager.removeGreater(userCommand[1]))
                    isOk = true;
                break;
            case "save":
                if (commandManager.save(userCommand[1]))
                    isOk = true;
                break;
            case "show":
                if (commandManager.show(userCommand[1]))
                    isOk = true;
                break;
            case "update":
                if (commandManager.update(userCommand[1]))
                    isOk = true;
                break;
            default:
                printError("There is no such command");
                break;
        }
        return new boolean[]{isOk, isExit};
    }
    public static void printError(Object o) {
        System.out.println("ERROR! " + o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }
    public static void println(Object o) {
        System.out.println(o);
    }
    public static void printDescription(Object o1, Object o2){
        System.out.printf("%s: %s\n", o1, o2);
    }
}
