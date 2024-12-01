package lt.sukram.solutions;

import java.util.List;

public class NoSolution implements DailySolution {

    @Override
    public String firstPart(List<String> input) {
        return "No solution";
    }

    @Override
    public String secondPart(List<String> input) {
        return "No solution";
    }

    @Override
    public int day() {
        return 0;
    }
}
