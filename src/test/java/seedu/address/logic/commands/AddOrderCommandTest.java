package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Phone;
import seedu.address.testutil.OrderBuilder;


public class AddOrderCommandTest {
    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        AddOrderCommandTest.ModelStubAcceptingOrderAdded modelStub = new AddOrderCommandTest
                .ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();

        CommandResult commandResult = new AddOrderCommand(new Details(OrderBuilder.DEFAULT_DETAILS),
                new Phone(OrderBuilder.DEFAULT_PHONE)).execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_ORDER_SUCCESS, validOrder),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.ordersAdded);
    }

    @Test
    public void execute_duplicateOrder_throwsCommandException() {
        Order validOrder = new OrderBuilder().build();
        AddOrderCommand addOrderCommand = new AddOrderCommand(new Details(OrderBuilder.DEFAULT_DETAILS),
                new Phone(OrderBuilder.DEFAULT_PHONE));

        ModelStub modelStub = new AddOrderCommandTest.ModelStubWithOrder(validOrder);

        assertThrows(CommandException.class,
                AddOrderCommand.MESSAGE_DUPLICATE_ORDER, () -> addOrderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddOrderCommand addAliceOrderCommand = new AddOrderCommand(new Details(OrderBuilder.DEFAULT_DETAILS),
                new Phone("12345"));
        AddOrderCommand addBobOrderCommand = new AddOrderCommand(new Details(OrderBuilder.DEFAULT_DETAILS),
                new Phone("54321"));

        // same object -> returns true
        assertTrue(addAliceOrderCommand.equals(addAliceOrderCommand));

        // same values -> returns true
        AddOrderCommand addAliceOrderCommandCopy = new AddOrderCommand(new Details(OrderBuilder.DEFAULT_DETAILS),
                new Phone("12345"));
        assertTrue(addAliceOrderCommand.equals(addAliceOrderCommandCopy));

        // different types -> returns false
        assertFalse(addAliceOrderCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceOrderCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceOrderCommand.equals(addBobOrderCommand));
    }

    /**
     * A Model stub that contains a single Order.
     */
    private class ModelStubWithOrder extends ModelStub {
        private final Order order;

        ModelStubWithOrder(Order order) {
            requireNonNull(order);
            this.order = order;
        }

        @Override
        public boolean hasOrder(Order order) {
            requireNonNull(order);
            return this.order.equals(order);
        }
    }

    /**
     * A Model stub that always accept the order being added.
     */
    private class ModelStubAcceptingOrderAdded extends ModelStub {
        final ArrayList<Order> ordersAdded = new ArrayList<>();

        @Override
        public boolean hasOrder(Order order) {
            requireNonNull(order);
            return ordersAdded.stream().anyMatch(order::equals);
        }

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
