package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayOneSolutionTest {

    private DailySolution dayOneSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        dayOneSolution = new DayOneSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(1);
    }

    @Test
    void test_part1() {
        String result = dayOneSolution.firstPart(input);
        assertEquals("11", result);
    }

    @Test
    void test_part2() {
        String result = dayOneSolution.secondPart(input);
        assertEquals("31", result);
    }
}