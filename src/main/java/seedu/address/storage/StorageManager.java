package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ReadyBakey data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ReadyBakeyStorage readyBakeyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ReadyBakeyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ReadyBakeyStorage readyBakeyStorage, UserPrefsStorage userPrefsStorage) {
        this.readyBakeyStorage = readyBakeyStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ReadyBakey methods ==============================

    @Override
    public Path getReadyBakeyFilePath() {
        return readyBakeyStorage.getReadyBakeyFilePath();
    }

    @Override
    public Optional<seedu.address.model.ReadOnlyReadyBakey> readReadyBakey() throws DataConversionException,
            IOException {
        return readReadyBakey(readyBakeyStorage.getReadyBakeyFilePath()); //Obtaining file path
    }

    @Override
    public Optional<seedu.address.model.ReadOnlyReadyBakey> readReadyBakey(Path filePath) throws
            DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return readyBakeyStorage.readReadyBakey(filePath);
    }

    @Override
    public void saveReadyBakey(seedu.address.model.ReadOnlyReadyBakey addressBook) throws IOException {
        saveReadyBakey(addressBook, readyBakeyStorage.getReadyBakeyFilePath());
    }

    @Override
    public void saveReadyBakey(seedu.address.model.ReadOnlyReadyBakey addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        readyBakeyStorage.saveReadyBakey(addressBook, filePath);
    }

}
