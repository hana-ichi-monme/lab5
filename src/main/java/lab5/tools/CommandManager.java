package lab5.tools;

import lab5.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<Command> commands = new ArrayList<>();
    private Command addCommand;
    private Command addIfMinCommand;
    private Command clearCommand;
    private Command countByHealthCommand;
    private Command executeScriptCommand;
    private Command exitCommand;
    private Command helpCommand;
    private Command infoCommand;
    private Command maxByHealthCommand;
    private Command printUniqueChapterCommand;
    private Command removeByIdCommand;
    private Command removeFirstCommand;
    private Command removeGreaterCommand;
    private Command saveCommand;
    private Command showCommand;
    private Command updateCommand;

    public CommandManager(
            Command addCommand,
            Command addIfMinCommand,
            Command clearCommand,
            Command countByHealthCommand,
            Command executeScriptCommand,
            Command exitCommand,
            Command helpCommand,
            Command infoCommand,
            Command maxByHealthCommand,
            Command printUniqueChapterCommand,
            Command removeByIdCommand,
            Command removeFirstCommand,
            Command removeGreaterCommand,
            Command saveCommand,
            Command showCommand,
            Command updateCommand) {
        this.addCommand = addCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.clearCommand = clearCommand;
        this.countByHealthCommand = countByHealthCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.maxByHealthCommand = maxByHealthCommand;
        this.printUniqueChapterCommand = printUniqueChapterCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeFirstCommand = removeFirstCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;

        commands.add(addCommand);
        commands.add(addIfMinCommand);
        commands.add(clearCommand);
        commands.add(countByHealthCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(maxByHealthCommand);
        commands.add(printUniqueChapterCommand);
        commands.add(removeByIdCommand);
        commands.add(removeFirstCommand);
        commands.add(removeGreaterCommand);
        commands.add(saveCommand);
        commands.add(showCommand);
        commands.add(updateCommand);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMin(String argument) {
        return addIfMinCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean countByHealth(String argument) {
        return countByHealthCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            commands.forEach(command -> ConsoleManager.printDescription(command.getName(), command.getInfo()));
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean maxByHealth(String argument) {
        return maxByHealthCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean printUniqueChapter(String argument) {
        return printUniqueChapterCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeFirst(String argument) {
        return removeFirstCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }
}
