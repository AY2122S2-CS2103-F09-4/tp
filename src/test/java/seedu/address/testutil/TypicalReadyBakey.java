package seedu.address.testutil;

import static seedu.address.testutil.TypicalOrders.getTypicalOrders;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.order.Order;
import seedu.address.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalReadyBakey {

    private TypicalReadyBakey() {} // prevents instantiation

    /**
     * Returns an {@code ReadyBakey} with all the typical persons.
     */
    public static seedu.address.model.ReadyBakey getTypicalReadyBakey() {
        seedu.address.model.ReadyBakey ab = new seedu.address.model.ReadyBakey();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }


}
