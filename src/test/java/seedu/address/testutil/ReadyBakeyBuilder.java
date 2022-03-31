package seedu.address.testutil;

import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ReadyBakey ab = new ReadyBakeyBuilder().withPerson("John", "Doe").build();}
 */
public class ReadyBakeyBuilder {

    private seedu.address.model.ReadyBakey addressBook;

    public ReadyBakeyBuilder() {
        addressBook = new seedu.address.model.ReadyBakey();
    }

    public ReadyBakeyBuilder(seedu.address.model.ReadyBakey readyBakey) {
        this.addressBook = readyBakey;
    }

    /**
     * Adds a new {@code Person} to the {@code ReadyBakey} that we are building.
     */
    public ReadyBakeyBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public seedu.address.model.ReadyBakey build() {
        return addressBook;
    }
}
