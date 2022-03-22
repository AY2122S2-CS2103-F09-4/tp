package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.order.Order;

public class TypicalOrders {
    public static final Order EMILY = new OrderBuilder()
            .withUuid("237e9877-e79b-12d4-a765-321741963000")
            .withDetails("1xvanillacake")
            .withComplete(false).build();

    public static final Order SIMON = new OrderBuilder()
            .withUuid("ea3cb232-f297-451c-80d8-c1800fef118f")
            .withDetails("1xchocolatecake")
            .withComplete(false).build();

    public static final Order JERRY = new OrderBuilder()
            .withUuid("15580af3-4d85-4948-ba35-628962989444")
            .withDetails("1xjerryfavouritecheesecake")
            .withComplete(false).build();

    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalAddressBookOrders() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(EMILY, SIMON, JERRY));
    }
}
