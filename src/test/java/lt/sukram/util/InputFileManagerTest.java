package lt.sukram.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputFileManagerTest {

    @TempDir
    private Path inputDir;

    @Mock
    private AocInputClient aocInputClient;

    private InputFileManager inputFileManager;

    @BeforeEach
    void setUp() {
        inputFileManager = new InputFileManager(inputDir.toString(), aocInputClient);
    }

    @Test
    void givenInputDay_fileExists_returnsInputFileName() throws Exception {
        String testFile = "input-day-42.txt";
        Files.createFile(inputDir.resolve(testFile));
        String inputFile = inputFileManager.getOrPrepareInputFile(42);
        assertEquals(testFile, inputFile);
    }

    @Test
    void givenInputDay_fileDoesNotExist_downloadsInputAndReturnsFileName() {
        when(aocInputClient.fetchInputForDay(55)).thenReturn("");
        String inputFile = inputFileManager.getOrPrepareInputFile(55);

        assertEquals("input-day-55.txt", inputFile);
        verify(aocInputClient).fetchInputForDay(55);
    }
}