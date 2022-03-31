package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyReadyBakey;

/**
 * A class to access ReadyBakey data stored as a json file on the hard disk.
 */
public class JsonReadyBakeyStorage implements ReadyBakeyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonReadyBakeyStorage.class);

    private Path filePath;

    public JsonReadyBakeyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getReadyBakeyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyReadyBakey> readReadyBakey() throws DataConversionException {
        return readReadyBakey(filePath);
    }

    /**
     * Similar to {@link #readReadyBakey()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyReadyBakey> readReadyBakey(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableReadyBakey> jsonReadyBakey = JsonUtil.readJsonFile(
                filePath, JsonSerializableReadyBakey.class);
        if (!jsonReadyBakey.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonReadyBakey.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveReadyBakey(ReadOnlyReadyBakey readyBakey) throws IOException {
        saveReadyBakey(readyBakey, filePath);
    }

    /**
     * Similar to {@link #saveReadyBakey(ReadOnlyReadyBakey)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveReadyBakey(ReadOnlyReadyBakey readyBakey, Path filePath) throws IOException {
        requireNonNull(readyBakey);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableReadyBakey(readyBakey), filePath);
    }

}
