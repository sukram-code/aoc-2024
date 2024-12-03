package lt.sukram.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DayThreeSolution implements DailySolution {

    private static final String MULTIPLY_COMMAND = "mul";
    private static final String DO_COMMAND = "do()";
    private static final String DONT_COMMAND = "don't()";

    @Override
    public String firstPart(List<String> input) {
        int result = 0;
        for (String line : input) {
            result += multiplicationInLine(line);
        }
        return String.valueOf(result);
    }

    @Override
    public String secondPart(List<String> input) {
        boolean multiplicationEnabled = true;
        List<String> enabledSections = new ArrayList<>();
        for (String line : input) {
            String str = line;
            while (!str.isEmpty()) {
                MultiplicationEnabledCheckResult checkResult = multiplicationEnabled ? lookForDisabledZone(str) : lookForEnabledZone(str);
                checkResult.enabledSection.ifPresent(enabledSections::add);
                str = checkResult.str();
                multiplicationEnabled = checkResult.multiplicationEnabled();
            }
        }
        return String.valueOf(enabledSections.stream().mapToInt(this::multiplicationInLine).sum());
    }

    private int multiplicationInLine(String line) {
        int result = 0;
        int start = 0;
        int end = line.length() - 1;
        while (start < end && line.indexOf(MULTIPLY_COMMAND, start) != -1) {
            int commandIndex = line.indexOf(MULTIPLY_COMMAND, start);
            int mul = extractValidMul(line.substring(commandIndex));
            if (mul > 0) {
                result += mul;
            }
            start = commandIndex + 3;
        }
        return result;
    }

    private int extractValidMul(String str) {
        int minValidLength = 8;
        if (str.length() < minValidLength) {
            return -1;
        }
        String args = str.substring(MULTIPLY_COMMAND.length());
        if (!args.startsWith("(") || args.indexOf(")") < 4 || args.indexOf(")") > 11) {
            return -1;
        }
        String numbersSplitByComma = args.substring(1, args.indexOf(")"));
        if (!numbersSplitByComma.contains(",")) {
            return -1;
        }
        String[] numbers = numbersSplitByComma.split(",");
        if (numbers.length != 2) {
            return -1;
        }
        String firstNumber = numbers[0];
        if (firstNumber.isEmpty() || firstNumber.length() > 3) {
            return -1;
        }
        String secondNumber = numbers[1];
        if (secondNumber.isEmpty() || secondNumber.length() > 3) {
            return -1;
        }
        try {
            return Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private MultiplicationEnabledCheckResult lookForEnabledZone(String input) {
        if (input.contains(DO_COMMAND)) {
            return new MultiplicationEnabledCheckResult(
                    true,
                    Optional.empty(),
                    input.substring(input.indexOf(DO_COMMAND) + DO_COMMAND.length())
            );
        } else {
            return new MultiplicationEnabledCheckResult(
                    false,
                    Optional.empty(),
                    ""
            );
        }
    }

    private MultiplicationEnabledCheckResult lookForDisabledZone(String input) {
        if (input.contains(DONT_COMMAND)) {
            return new MultiplicationEnabledCheckResult(
                    false,
                    Optional.of(input.substring(0, input.indexOf(DONT_COMMAND) + DONT_COMMAND.length())),
                    input.substring(input.indexOf(DONT_COMMAND) + DONT_COMMAND.length())
            );
        } else {
            return new MultiplicationEnabledCheckResult(
                    true,
                    Optional.of(input),
                    ""
            );
        }
    }

    record MultiplicationEnabledCheckResult(
            boolean multiplicationEnabled,
            Optional<String> enabledSection,
            String str
    ) {
    }

    @Override
    public int day() {
        return 3;
    }
}
