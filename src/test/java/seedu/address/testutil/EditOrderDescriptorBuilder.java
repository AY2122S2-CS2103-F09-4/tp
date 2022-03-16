package seedu.address.testutil;

import seedu.address.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.order.Order;
import seedu.address.model.person.Phone;
import seedu.address.model.order.Details;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {

    private EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderDescriptor();
    }

    public EditOrderDescriptorBuilder(EditOrderDescriptor descriptor) {
        this.descriptor = new EditOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditOrderDescriptor();
        descriptor.setName(order.getName());
        descriptor.setPhone(order.getPhone());
        descriptor.setAddress(order.getAddress());
        descriptor.setDetails(order.getDetails());
    }

    /**
     * Sets the {@code Name} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Details} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withDetails(String details) {
        descriptor.setDetails(new Details(details));
        return this;
    }

    public EditOrderDescriptor build() {
        return descriptor;
    }
}
