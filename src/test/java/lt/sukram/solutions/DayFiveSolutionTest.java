package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayFiveSolutionTest {

    private DailySolution dayFiveSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        dayFiveSolution = new DayFiveSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(5);
    }

    @Test
    void test_part1() {
        String result = dayFiveSolution.firstPart(input);
        assertEquals("143", result);
    }

    @Test
    void test_part2() {
        String result = dayFiveSolution.secondPart(input);
        assertEquals("123", result);
    }
}