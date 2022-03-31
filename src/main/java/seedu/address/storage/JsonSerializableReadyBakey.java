package seedu.address.storage;

import static seedu.address.logic.commands.AddOrderCommand.MESSAGE_DUPLICATE_ORDER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * An Immutable ReadyBakey that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableReadyBakey {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableReadyBakey} with the given persons.
     */
    @JsonCreator
    public JsonSerializableReadyBakey(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyReadyBakey} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableReadyBakey}.
     */
    public JsonSerializableReadyBakey(seedu.address.model.ReadOnlyReadyBakey source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ReadyBakey} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public seedu.address.model.ReadyBakey toModelType() throws IllegalValueException {
        seedu.address.model.ReadyBakey readyBakey = new seedu.address.model.ReadyBakey();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (readyBakey.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            readyBakey.addPerson(person);
        }

        // Adding orders
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (readyBakey.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            readyBakey.addOrder(order);
        }

        return readyBakey;
    }

}
