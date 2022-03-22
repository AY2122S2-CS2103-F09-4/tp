package seedu.address.storage;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Complete;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String details;
    private final String complete;
    private final String uuid;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("details") String details,
                             @JsonProperty("complete") String complete,
                             @JsonProperty("uuid") String uuid) {
        this.details = details;
        this.complete = complete;
        this.uuid = uuid;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        details = source.getDetails().value;
        complete = source.getComplete().value.toString();
        uuid = source.getUuid().toString();

    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (details == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Details.class.getSimpleName()));
        }
        if (!Details.isValidDetails(details)) {
            throw new IllegalValueException(Details.MESSAGE_CONSTRAINTS);
        }
        final Details modelDetails = new Details(details);

        if (complete == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Complete.class.getSimpleName())
            );
        }
        if (!Complete.isValidComplete(complete)) {
            throw new IllegalValueException(Complete.MESSAGE_CONSTRAINTS);
        }
        final Complete modelComplete = new Complete(complete);

        final UUID modelUuid;
        try {
            modelUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(Complete.MESSAGE_CONSTRAINTS);
        }

        return new Order(modelDetails, modelComplete, modelUuid);
    }

}
