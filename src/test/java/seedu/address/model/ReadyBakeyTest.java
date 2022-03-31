package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalReadyBakeyPersons;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ReadyBakeyTest {

    private final ReadyBakey readyBakey = new ReadyBakey();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), readyBakey.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readyBakey.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyReadyBakey_replacesData() {
        ReadyBakey newData = getTypicalReadyBakeyPersons();
        readyBakey.resetData(newData);
        assertEquals(newData, readyBakey);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        seedu.address.model.ReadyBakeyTest.ReadyBakeyStub newData = new ReadyBakeyTest.ReadyBakeyStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> readyBakey.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readyBakey.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInReadyBakey_returnsFalse() {
        assertFalse(readyBakey.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInReadyBakey_returnsTrue() {
        readyBakey.addPerson(ALICE);
        assertTrue(readyBakey.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInReadyBakey_returnsTrue() {
        readyBakey.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(readyBakey.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> readyBakey.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyReadyBakey whose persons list can violate interface constraints.
     */
    private static class ReadyBakeyStub implements ReadOnlyReadyBakey {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Order> orders = FXCollections.observableArrayList();


        ReadyBakeyStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Order> getOrderList() {
            return orders;
        }
    }

}
