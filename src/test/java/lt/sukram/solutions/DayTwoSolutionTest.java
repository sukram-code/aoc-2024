package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayTwoSolutionTest {

    private DailySolution dayTwoSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        dayTwoSolution = new DayTwoSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(2);
    }

    @Test
    void test_part1() {
        String result = dayTwoSolution.firstPart(input);
        assertEquals("2", result);
    }

    @Test
    void test_part2() {
        String result = dayTwoSolution.secondPart(input);
        assertEquals("4", result);
    }
}