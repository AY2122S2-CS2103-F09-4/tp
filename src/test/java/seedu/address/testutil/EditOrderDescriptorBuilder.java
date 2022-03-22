package seedu.address.testutil;

import seedu.address.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;

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
        descriptor.setDetails(order.getDetails());
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
