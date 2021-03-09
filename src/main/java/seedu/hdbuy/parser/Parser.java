package seedu.hdbuy.parser;

import seedu.hdbuy.command.CloseCommand;
import seedu.hdbuy.command.Command;
import seedu.hdbuy.command.DefaultCommand;
import seedu.hdbuy.command.FilterCommand;
import seedu.hdbuy.command.FindCommand;
import seedu.hdbuy.command.HelpCommand;
import seedu.hdbuy.data.CommandKey;

public class Parser {
    private static final String HELP = "help";
    private static final String FILTER = "filter";
    private static final String FIND = "find";
    private static final String EXIT = "exit";

    public static Command parse(String fullLine) {
        Command command = new DefaultCommand(fullLine);
        CommandKey keyCommand = extractInfo(fullLine);
        switch (keyCommand.getCommand()) {
        case HELP:
            command = new HelpCommand();
            break;
        case FILTER:
            String criteria = keyCommand.getCriteria();
            String value = keyCommand.getValue();
            command = new FilterCommand(criteria, value);
            break;
        case FIND:
            command = new FindCommand();
            break;
        case EXIT:
            command = new CloseCommand();
            break;
        default:
            break;
        }
        return command;
    }

    public static CommandKey extractInfo(String fullLine) {
        String[] lineParts;
        lineParts = fullLine.split(" ");
        String keyCommand = lineParts[0];
        if (keyCommand.equals(FILTER)) {
            String criteria = lineParts[1];
            String value = lineParts[2];
            return new CommandKey(criteria, value, keyCommand);
        }
        return new CommandKey(keyCommand);
    }
}