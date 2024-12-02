package lt.sukram.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DayTwoSolution implements DailySolution {

    private static final int MIN_LEVEL_DIFFERENCE = 1;
    private static final int MAX_LEVEL_DIFFERENCE = 3;

    @Override
    public String firstPart(List<String> input) {
        return String.valueOf(reports(input)
                .filter(it -> defineReportType(it.levels()) == ReportType.SAFE)
                .count());
    }

    @Override
    public String secondPart(List<String> input) {
        return String.valueOf(reports(input)
                .filter(it -> defineReportType(it.levels(), true) == ReportType.SAFE)
                .count());
    }

    @Override
    public int day() {
        return 2;
    }

    private Stream<Report> reports(List<String> input) {
        return input.stream()
                .map(it -> it.split("\\s+"))
                .map(Arrays::asList)
                .map(report -> new Report(report.stream().map(level -> new Level(Integer.parseInt(level))).toList()));
    }

    private ReportType defineReportType(List<Level> levels, boolean tolerateSingleFault) {
        LevelPattern startingPattern = LevelPattern.UNDEFINED;
        int previousLevel = levels.getFirst().value();
        for (int i = 1; i < levels.size(); i++) {
            int currentLevel = levels.get(i).value();

            int levelDifference = Math.abs(currentLevel - previousLevel);
            if (levelDifference < MIN_LEVEL_DIFFERENCE || levelDifference > MAX_LEVEL_DIFFERENCE) {
                return tolerateSingleFault ? lookForFault(levels) : ReportType.UNSAFE;
            }

            if (startingPattern == LevelPattern.UNDEFINED) {
                startingPattern = currentLevel > previousLevel ? LevelPattern.INCREASING : LevelPattern.DECREASING;
            }

            LevelPattern pattern = currentLevel > previousLevel ? LevelPattern.INCREASING : LevelPattern.DECREASING;
            if (pattern != startingPattern) {
                return tolerateSingleFault ? lookForFault(levels) : ReportType.UNSAFE;
            }

            previousLevel = currentLevel;
        }
        return ReportType.SAFE;
    }

    enum ReportType {
        SAFE, UNSAFE

    }
    enum LevelPattern {
        INCREASING, DECREASING, UNDEFINED

    }
    record Report(List<Level> levels) {

    }
    record Level(int value) {
        @Override
        public String toString() {
            return String.valueOf(value);
        }

    }

    private ReportType defineReportType(List<Level> levels) {
        return defineReportType(levels, false);
    }

    private ReportType lookForFault(List<Level> levels) {
        for (int i = 0; i < levels.size(); i++) {
            List<Level> attemptWithRemovedLevel = new ArrayList<>(levels);
            attemptWithRemovedLevel.remove(i);
            ReportType reportType = defineReportType(attemptWithRemovedLevel);
            if (reportType == ReportType.SAFE) {
                return ReportType.SAFE;
            }
        }
        return ReportType.UNSAFE;
    }
}
