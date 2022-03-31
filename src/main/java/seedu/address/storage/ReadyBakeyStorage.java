package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReadyBakey;

/**
 * Represents a storage for {@link seedu.address.model.ReadyBakey}.
 */
public interface ReadyBakeyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getReadyBakeyFilePath();

    /**
     * Returns ReadyBakey data as a {@link ReadOnlyReadyBakey}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<seedu.address.model.ReadOnlyReadyBakey> readReadyBakey() throws DataConversionException, IOException;

    /**
     * @see #getReadyBakeyFilePath()
     */
    Optional<seedu.address.model.ReadOnlyReadyBakey> readReadyBakey(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyReadyBakey} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveReadyBakey(seedu.address.model.ReadOnlyReadyBakey addressBook) throws IOException;

    /**
     * @see #saveReadyBakey(ReadOnlyReadyBakey)
     */
    void saveReadyBakey(seedu.address.model.ReadOnlyReadyBakey addressBook, Path filePath) throws IOException;

}
