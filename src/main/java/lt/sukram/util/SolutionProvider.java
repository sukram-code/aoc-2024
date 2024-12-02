package lt.sukram.util;

import lt.sukram.solutions.DailySolution;
import lt.sukram.solutions.DayOneSolution;
import lt.sukram.solutions.DayTwoSolution;
import lt.sukram.solutions.NoSolution;

import java.util.Set;

public class SolutionProvider {

    private final Set<DailySolution> solutions;

    public SolutionProvider() {
        this.solutions = Set.of(
                new DayOneSolution(),
                new DayTwoSolution()
        );
    }

    public DailySolution getSolutionOfDay(int day) {
        return solutions.stream()
                .filter(it -> it.day() == day)
                .findFirst()
                .orElse(new NoSolution());
    }
}
