package seedu.address.testutil;

import java.util.UUID;

import seedu.address.model.order.Complete;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;

public class OrderBuilder {

    public static final String DEFAULT_DETAILS = "1xchocolatemuffin";
    public static final String DEFAULT_COMPLETE = "false";
    public static final String DEFAULT_UUID = "c6a8669e-ee95-4c42-9ef6-4a9b61380164";
    public static final String DEFAULT_PHONE = "85355255";

    private Details details;
    private Complete complete;
    private UUID uuid;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        details = new Details(DEFAULT_DETAILS);
        complete = new Complete(DEFAULT_COMPLETE);
        uuid = UUID.fromString(DEFAULT_UUID);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        uuid = orderToCopy.getUuid();
        details = orderToCopy.getDetails();
        complete = orderToCopy.getComplete();
    }


    /**
     * Sets the {@code UUID} of the {@code Order} that we are building.
     */
    public OrderBuilder withUuid(String uuid) {
        this.uuid = UUID.fromString(uuid);
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
        return new Order(details, complete, uuid);
    }
}
