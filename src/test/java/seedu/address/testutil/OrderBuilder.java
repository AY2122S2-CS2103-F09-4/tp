package seedu.address.testutil;

import seedu.address.model.order.Complete;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class OrderBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DETAILS = "1xchocolatemuffin";
    public static final String DEFAULT_COMPLETE = "false";

    private Name name;
    private Phone phone;
    private Address address;
    private Details details;
    private Complete complete;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        details = new Details(DEFAULT_DETAILS);
        complete = new Complete(DEFAULT_COMPLETE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        name = orderToCopy.getName();
        phone = orderToCopy.getPhone();
        address = orderToCopy.getAddress();
        details = orderToCopy.getDetails();
        complete = orderToCopy.getComplete();
    }

    /**
     * Sets the {@code Name} of the {@code Order} that we are building.
     */
    public OrderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Order} that we are building.
     */
    public OrderBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Order} that we are building.
     */
    public OrderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code Order} that we are building.
     */
    public OrderBuilder withDetails(String details) {
        this.details = new Details(details);
        return this;
    }

    /**
     * Sets the {@code Complete} of the {@code Order} that we are building.
     */
    public OrderBuilder withComplete(Boolean complete) {
        this.complete = new Complete(complete);
        return this;
    }

    public Order build() {
        return new Order(name, phone, address, details, complete);
    }
}
