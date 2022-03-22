package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.OrderUuidContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 *  Finds and lists all orders in ReadyBakey whose predicate (Name) contains any of the argument keywords.
 *  Keyword matching is case insensitive.
 */
public class FindOrderNameCommand extends FindOrderCommand {

    private final Predicate<Person> predicate;

    public FindOrderNameCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> filteredList = model.getFilteredPersonList().filtered(predicate);
        String[] uuidKeywords = filteredList.stream().map(person->person.getUuid().toString()).toArray(String[]::new);
        model.updateFilteredOrderList(new OrderUuidContainsKeywordsPredicate(Arrays.asList(uuidKeywords)));
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrderNameCommand // instanceof handles nulls
                && predicate.equals(((FindOrderNameCommand) other).predicate)); // state check
    }
}
