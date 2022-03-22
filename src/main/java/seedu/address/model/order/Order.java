package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents an Order in the ReadyBakey.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {

    // Identity fields
    private final UUID uuid;

    // Data fields
    private final Details details;
    private final Complete complete;

    /**
     * Every field must be present and not null.
     */
    public Order(Details details, UUID uuid) {
        requireAllNonNull(uuid, details);
        this.details = details;
        this.complete = new Complete(false);
        this.uuid = uuid;
    }

    /**
     * Every field must be present and not null.
     */
    public Order(Details details, Complete complete, UUID uuid) {
        requireAllNonNull(uuid, details, complete);
        this.details = details;
        this.complete = complete;
        this.uuid = uuid;
    }

    public Details getDetails() {
        return details;
    }

    public Complete getComplete() {
        return complete;
    }

    public UUID getUuid() {
        return uuid;
    }


    /**
     * Returns true if both orders have the same identity and data fields.
     * This defines a stronger notion of equality between two orders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getUuid().equals(getUuid())
                && otherOrder.getDetails().equals(getDetails());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(uuid, details, complete);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Details: ")
                .append(getDetails())
                .append("; Complete: ")
                .append(getComplete());

        return builder.toString();
    }

}
