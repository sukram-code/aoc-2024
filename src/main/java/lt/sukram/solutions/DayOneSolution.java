package lt.sukram.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayOneSolution implements DailySolution {

    @Override
    public String firstPart(List<String> input) {
        Columns columns = getColumns(input).sorted();
        int result = 0;
        for (int i = 0; i < input.size(); i++) {
            result += Math.abs(columns.first().get(i) - columns.second().get(i));
        }
        return String.valueOf(result);
    }

    @Override
    public String secondPart(List<String> input) {
        Columns columns = getColumns(input);
        Map<Integer, Integer> firstColumnToAppearanceInSecondColumn = new HashMap<>();
        int result = 0;
        for (Integer number : columns.first()) {
            if (firstColumnToAppearanceInSecondColumn.containsKey(number)) {
                result += number * firstColumnToAppearanceInSecondColumn.get(number);
                continue;
            }
            int occurrences = (int) columns.second().stream().filter(it -> it.equals(number)).count();
            firstColumnToAppearanceInSecondColumn.put(number, occurrences);
            result += number * occurrences;
        }
        return String.valueOf(result);
    }

    @Override
    public int day() {
        return 1;
    }

    private Columns getColumns(List<String> input) {
        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();
        input.stream().map(line -> line.split("\\s+"))
                .forEach(pair -> {
                    firstColumn.add(Integer.parseInt(pair[0]));
                    secondColumn.add(Integer.parseInt(pair[1]));
                });
        return new Columns(firstColumn, secondColumn);
    }

    record Columns(
            List<Integer> first,
            List<Integer> second
    ) {
        public Columns sorted() {
            first.sort(Integer::compareTo);
            second.sort(Integer::compareTo);
            return this;
        }
    }
}
