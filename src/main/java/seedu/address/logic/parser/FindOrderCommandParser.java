package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.FindOrderNameCommand;
import seedu.address.logic.commands.FindOrderPhoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindOrderCommand object
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_NAME);

        Prefix findOrderPrefix;
        Predicate<Person> findPersonPredicate;
        if (isOnlyOnePrefixPresent(argMultimap, PREFIX_NAME)) {
            findOrderPrefix = PREFIX_NAME;
            String[] nameKeywords = argMultimap.getValue(findOrderPrefix).get().trim().split("\\s+");
            findPersonPredicate = new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords));
            return new FindOrderNameCommand(findPersonPredicate);
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_PHONE)) {
            findOrderPrefix = PREFIX_PHONE;
            String[] phoneKeywords = argMultimap.getValue(findOrderPrefix).get().trim().split("\\s+");
            findPersonPredicate = new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords));
            return new FindOrderPhoneCommand(findPersonPredicate);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }
    }

    private static boolean isOnlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix searchPrefix) {
        ArrayList<Prefix> argPrefixes = argumentMultimap.getAllPrefixes();
        // check that prefix exists
        boolean isSearchPrefixExists = argPrefixes.stream().filter(prefix -> prefix.equals(searchPrefix)).count() == 1;

        // check no other prefix exists except for blank prefix as by-product of tokenizing
        boolean noOtherPrefixExists = argPrefixes.stream()
                .filter(prefix -> !prefix.equals(searchPrefix) || prefix.equals(PREFIX_BLANK))
                .count() == 1;
        return isSearchPrefixExists && noOtherPrefixExists;
    }

}
