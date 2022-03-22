package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.address.testutil.EditOrderDescriptorBuilder;

public class EditOrderDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditOrderDescriptor descriptorWithSameValues = new EditOrderDescriptor(DESC_AMY_ORDER);
        assertTrue(DESC_AMY_ORDER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_ORDER.equals(DESC_AMY_ORDER));

        // null -> returns false
        assertFalse(DESC_AMY_ORDER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_ORDER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_ORDER.equals(DESC_BOB_ORDER));

        // different details -> returns false
        EditOrderDescriptor editedAmy = new EditOrderDescriptorBuilder(DESC_AMY_ORDER).withDetails(VALID_DETAILS_BOB)
                .build();
        assertFalse(DESC_AMY_ORDER.equals(editedAmy));

    }
}
