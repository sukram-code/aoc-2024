package lt.sukram.solutions;

import java.util.Arrays;
import java.util.List;

public class DaySevenSolution implements DailySolution {
    @Override
    public String firstPart(List<String> input) {
        List<Equation> equations = input.stream().map(it -> {
            String[] resultToParts = it.split(":");
            int result = Integer.parseInt(resultToParts[0]);
            List<Integer> parts = Arrays.stream(resultToParts[1].trim().split(" ")).map(Integer::parseInt).toList();
            return new Equation(result, parts);
        }).toList();
        return String.valueOf(equations.stream().filter(this::isValid).mapToInt(Equation::result).sum());
    }

    private boolean isValid(Equation equation) {
        int product = equation.parts().stream().reduce(1, (a, b) -> a * b);
        if (product == equation.result()) {
            return true;
        }
        int sum = equation.parts().stream().reduce(0, Integer::sum);
        if (sum == equation.result()) {
            return true;
        }
        // Single multiplication variations

        // Single addition variations
        return false;
    }

    @Override
    public String secondPart(List<String> input) {
        return "";
    }

    @Override
    public int day() {
        return 7;
    }

    record Equation(int result, List<Integer> parts) {}
}
