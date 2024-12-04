package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayFourSolutionTest {

    private DailySolution dayFourSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        dayFourSolution = new DayFourSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(4);
    }

    @Test
    void test_part1() {
        String result = dayFourSolution.firstPart(input);
        assertEquals("18", result);
    }

    @Test
    void test_part2() {
        String result = dayFourSolution.secondPart(input);
        assertEquals("9", result);
    }
}