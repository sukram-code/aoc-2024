package lt.sukram.util;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputProvider {

    private static final Logger log = Logger.getLogger(InputProvider.class.getName());

    private static final int ADVENT_START = 1;
    private static final int ADVENT_END = 25;

    private final InputFileManager inputFileManager;

    public InputProvider(InputFileManager inputFileManager) {
        this.inputFileManager = inputFileManager;
    }

    public List<String> getInputLines(int day) {
        if (day >= ADVENT_START && day <= ADVENT_END) {
            String inputFile = inputFileManager.getOrPrepareInputFile(day);
            return inputFileManager.readInputFromFile(inputFile);
        }
        log.log(Level.WARNING, "Invalid input day {0}, no input will be provided", day);
        return List.of();
    }
}
