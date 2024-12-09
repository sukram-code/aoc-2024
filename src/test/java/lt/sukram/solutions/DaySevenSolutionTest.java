package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaySevenSolutionTest {

    private DailySolution daySevenSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        daySevenSolution = new DaySevenSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(7);
    }

    @Test
    void test_part1() {
        String result = daySevenSolution.firstPart(input);
        assertEquals("3749", result);
    }

    @Test
    void test_part2() {
        String result = daySevenSolution.secondPart(input);
        assertEquals("3749", result);
    }
}