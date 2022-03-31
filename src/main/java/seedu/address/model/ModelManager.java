package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDateComparator;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ReadyBakey readyBakey;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Order> filteredOrders;
    private final OrderDateComparator orderDateComparator;
    private ObservableList<Order> orders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyReadyBakey readyBakey, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(readyBakey, userPrefs);

        logger.fine("Initializing with address book: " + readyBakey + " and user prefs " + userPrefs);

        this.readyBakey = new ReadyBakey(readyBakey);
        this.userPrefs = new UserPrefs(userPrefs);
        this.orderDateComparator = new OrderDateComparator();
        filteredPersons = new FilteredList<>(this.readyBakey.getPersonList());
        filteredOrders = new FilteredList<>(this.readyBakey.getOrderList());
        orders = filteredOrders;
    }

    public ModelManager() {
        this(new ReadyBakey(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getReadyBakeyFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setReadyBakeyFilePath(Path readyBakeyFilePath) {
        requireNonNull(readyBakeyFilePath);
        userPrefs.setReadyBakeyFilePath(readyBakeyFilePath);
    }

    //=========== ReadyBakey Person ================================================================================

    @Override
    public void setReadyBakey(ReadOnlyReadyBakey readyBakey) {
        this.readyBakey.resetData(readyBakey);
    }

    @Override
    public ReadOnlyReadyBakey getReadyBakey() {
        return readyBakey;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return readyBakey.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        readyBakey.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        readyBakey.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        readyBakey.setPerson(target, editedPerson);
    }

    //=========== ReadyBakey Order ================================================================================

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return readyBakey.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        readyBakey.removeOrder(target);
    }


    @Override
    public void addOrder(Order order) {
        readyBakey.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void setOrder(Order target, Order editedPerson) {
        requireAllNonNull(target, editedPerson);

        readyBakey.setOrder(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Order List Accessors =============================================================

    /**
     * Returns a view of the list of {@code Order} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return orders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
        orders = filteredOrders;
    }

    @Override
    public void updatedSortedOrderList(ObservableList<Order> orderList) {
        requireNonNull(orderList);
        orders = new SortedList<>(orderList, orderDateComparator);
    }

    @Override
    public void updatedSortedFilteredOrderList(Predicate<Order> predicate) {
        updateFilteredOrderList(predicate);
        updatedSortedOrderList(filteredOrders);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return readyBakey.equals(other.readyBakey)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredOrders.equals(other.filteredOrders);
    }

}
