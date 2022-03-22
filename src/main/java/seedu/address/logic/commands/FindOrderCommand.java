package seedu.address.logic.commands;

/**
 *  Finds and lists all orders in ReadyBakey whose predicate (attribute) contains any of the argument keywords.
 *  Search orders based on attributes is only current supported by {@code name} and {@code phone}.
 */
public abstract class FindOrderCommand extends Command {
    public static final String COMMAND_WORD = "findo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders based on attributes (name/phone) "
            + "whose attributes contain any of the specified keywords (case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD (n|p)/ [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "n/ alice bob charlie";
}
