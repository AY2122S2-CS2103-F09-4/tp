package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ReadyBakey} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Remark("Allergic to Peanuts"),
                getTagSet("friends"), UUID.fromString("6ea390c0-242c-4643-a547-21cc85773f88")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Remark("Allergic to Chicken"),
                getTagSet("colleagues", "friends"), UUID.fromString("0d13afaa-db95-4a2a-b64d-5270e39d243d")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Remark("Enjoys a pun or two"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Remark("My really close friend"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Remark("Hates Pistachios"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Remark("Loves Games and Anime"),
                getTagSet("colleagues"))
        };
    }

    public static Order[] getSampleOrders() {
        return new Order[]{
            new Order(new Remark("No Peanuts"), getDetailsList("3:Strawberry Cupcakes"),
                    new DeliveryDateTime("25-12-2022 15:30"), CollectionType.DELIVERY,
                    UUID.fromString("6ea390c0-242c-4643-a547-21cc85773f88")),
            new Order(new Remark("Please make delivery by 4pm latest"), getDetailsList("1:Chocolate Cake"),
                    new DeliveryDateTime("22-12-2022 16:00"), CollectionType.DELIVERY,
                    UUID.fromString("6ea390c0-242c-4643-a547-21cc85773f88")),
            new Order(new Remark("No candles needed"), getDetailsList("1:Cake"),
                    new DeliveryDateTime("12-12-2022 12:30"), CollectionType.PICKUP,
                    UUID.fromString("0d13afaa-db95-4a2a-b64d-5270e39d243d"))
        };
    }

    public static seedu.address.model.ReadOnlyReadyBakey getSampleAddressBook() {
        seedu.address.model.ReadyBakey sampleAb = new seedu.address.model.ReadyBakey();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static List<Details> getDetailsList(String... strings) {
        return Arrays.stream(strings)
                .map(Details::new)
                .collect(Collectors.toList());
    }

}
