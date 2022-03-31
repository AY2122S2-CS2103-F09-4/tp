package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalReadyBakeyPersons;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReadyBakey;
import seedu.address.model.ReadyBakey;

public class JsonReadyBakeyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonReadyBakeyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readReadyBakey_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readReadyBakey(null));
    }

    private java.util.Optional<ReadOnlyReadyBakey> readReadyBakey(String filePath) throws Exception {
        return new JsonReadyBakeyStorage(Paths.get(filePath)).readReadyBakey(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readReadyBakey("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readReadyBakey("notJsonFormatReadyBakey.json"));
    }

    @Test
    public void readReadyBakey_invalidPersonReadyBakey_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readReadyBakey("invalidPersonReadyBakey.json"));
    }

    @Test
    public void readReadyBakey_invalidAndValidPersonReadyBakey_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readReadyBakey("invalidAndValidPersonReadyBakey.json"));
    }

    @Test
    public void readAndSaveReadyBakey_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempReadyBakey.json");
        ReadyBakey original = getTypicalReadyBakeyPersons();
        JsonReadyBakeyStorage jsonReadyBakeyStorage = new JsonReadyBakeyStorage(filePath);

        // Save in new file and read back
        jsonReadyBakeyStorage.saveReadyBakey(original, filePath);
        ReadOnlyReadyBakey readBack = jsonReadyBakeyStorage.readReadyBakey(filePath).get();
        assertEquals(original, new ReadyBakey(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonReadyBakeyStorage.saveReadyBakey(original, filePath);
        readBack = jsonReadyBakeyStorage.readReadyBakey(filePath).get();
        assertEquals(original, new ReadyBakey(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonReadyBakeyStorage.saveReadyBakey(original); // file path not specified
        readBack = jsonReadyBakeyStorage.readReadyBakey().get(); // file path not specified
        assertEquals(original, new ReadyBakey(readBack));

    }

    @Test
    public void saveReadyBakey_nullReadyBakey_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveReadyBakey(null, "SomeFile.json"));
    }

    /**
     * Saves {@code readyBakey} at the specified {@code filePath}.
     */
    private void saveReadyBakey(ReadOnlyReadyBakey readyBakey, String filePath) {
        try {
            new JsonReadyBakeyStorage(Paths.get(filePath))
                    .saveReadyBakey(readyBakey, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveReadyBakey_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveReadyBakey(new ReadyBakey(), null));
    }
}
