package lt.sukram.util;

import lt.sukram.solutions.DailySolution;
import lt.sukram.solutions.DayFiveSolution;
import lt.sukram.solutions.DayFourSolution;
import lt.sukram.solutions.DayOneSolution;
import lt.sukram.solutions.DaySixSolution;
import lt.sukram.solutions.DayThreeSolution;
import lt.sukram.solutions.DayTwoSolution;
import lt.sukram.solutions.NoSolution;

import java.util.Set;

public class SolutionProvider {

    private final Set<DailySolution> solutions;

    public SolutionProvider() {
        this.solutions = Set.of(
                new DayOneSolution(),
                new DayTwoSolution(),
                new DayThreeSolution(),
                new DayFourSolution(),
                new DayFiveSolution(),
                new DaySixSolution()
        );
    }

    public DailySolution getSolutionOfDay(int day) {
        return solutions.stream()
                .filter(it -> it.day() == day)
                .findFirst()
                .orElse(new NoSolution());
    }
}
