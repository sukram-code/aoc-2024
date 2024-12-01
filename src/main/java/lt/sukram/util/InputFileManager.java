package lt.sukram.util;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputFileManager {

    private static final Logger log = Logger.getLogger(InputFileManager.class.getName());
    private static final String INPUT_FILE_TEMPLATE = "input-day-%02d.txt";

    private final String inputFileDirectory;
    private final AocInputClient aocInputClient;

    public InputFileManager(String inputFileDirectory, AocInputClient aocInputClient) {
        this.inputFileDirectory = inputFileDirectory;
        this.aocInputClient = aocInputClient;
    }

    public String getOrPrepareInputFile(int day) {
        String inputFileName = String.format(INPUT_FILE_TEMPLATE, day);
        if (!inputFileExists(inputFileName)) {
            inputFileName = downloadAndSaveInput(day, inputFileName);
        }
        return inputFileName;
    }

    @SuppressWarnings("ConstantConditions")
    public List<String> readInputFromFile(String inputFile) {
        if (inputFileExists(inputFile)) {
            try {
                return Files.readAllLines(Path.of(inputFileDirectory + "/" + inputFile));
            } catch (Exception e) {
                log.log(Level.WARNING, "Failed to read {0}", inputFile);
            }
        }
        return List.of();
    }

    private boolean inputFileExists(String fileName) {
        return Files.exists(Path.of(inputFileDirectory + "/" + fileName));
    }

    private String downloadAndSaveInput(int day, String inputFileName) {
        String input = aocInputClient.fetchInputForDay(day);
        return writeToFile(input, inputFileName);
    }

    private String writeToFile(String input, String inputFileName) {
        try (FileWriter writer = new FileWriter(inputFileDirectory + "/" + inputFileName)) {
            writer.write(input);
        } catch (Exception e) {
            log.log(Level.WARNING, "Failure writing input to file {0}", inputFileName);
        }
        return inputFileName;
    }
}
