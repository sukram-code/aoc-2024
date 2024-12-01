package lt.sukram;

import lt.sukram.solutions.DailySolution;
import lt.sukram.util.AocInputClient;
import lt.sukram.util.CustomLogFormatter;
import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import lt.sukram.util.SolutionProvider;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger("advent-of-code");

    public static void main(String[] args) {
        configureLogger();
        CommandLineArguments commandLineArguments = parseCommandLineArguments(args);
        log.log(Level.INFO, "Running AoC 2024 with {0}", commandLineArguments);

        SolutionProvider solutionProvider = new SolutionProvider();
        DailySolution dailySolution = solutionProvider.getSolutionOfDay(commandLineArguments.day());

        InputProvider inputProvider = new InputProvider(new InputFileManager(commandLineArguments.inputFileDirectory(), new AocInputClient(commandLineArguments.sessionCookie())));
        List<String> input = inputProvider.getInputLines(commandLineArguments.day());

        log.log(Level.INFO, "=== Advent of Code - Day {0} ====", commandLineArguments.day());
        log.log(Level.INFO, "=== Solution of part {0} ====", 1);
        log.log(Level.INFO, "{0}", dailySolution.firstPart(input));
        log.log(Level.INFO, "=== Solution of part {0} ====", 2);
        log.log(Level.INFO, "{0}", dailySolution.secondPart(input));
    }

    private static void configureLogger() {
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }
        ConsoleHandler consoleHandler = new ConsoleHandler() {
            @Override
            protected void setOutputStream(OutputStream out) throws SecurityException {
                super.setOutputStream(System.out);
            }
        };
        consoleHandler.setFormatter(new CustomLogFormatter());
        rootLogger.addHandler(consoleHandler);
        rootLogger.setUseParentHandlers(false);
    }

    private static CommandLineArguments parseCommandLineArguments(String[] args) {
        int day = Arrays.stream(args).filter(it -> it.startsWith("--day"))
                .findFirst()
                .map(it -> Integer.parseInt(it.split("=")[1]))
                .orElse(LocalDate.now().getDayOfMonth());
        String inputs = Arrays.stream(args).filter(it -> it.startsWith("--inputs"))
                .findFirst()
                .map(it -> it.split("=")[1])
                .orElse("src/main/resources");
        String sessionCookie = Arrays.stream(args).filter(it -> it.startsWith("--session"))
                .findFirst()
                .map(it -> it.split("=")[1])
                .orElse("");
        return new CommandLineArguments(day, inputs, sessionCookie);
    }

    record CommandLineArguments(
            int day,
            String inputFileDirectory,
            String sessionCookie
    ) {}
}