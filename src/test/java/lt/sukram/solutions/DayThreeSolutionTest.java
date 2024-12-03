package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DayThreeSolutionTest {

    private DailySolution dayThreeSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        dayThreeSolution = new DayThreeSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(3);
    }

    @Test
    void test_part1() {
        String result = dayThreeSolution.firstPart(input);
        assertEquals("161", result);
    }

    @Test
    void test_part2() {
        String result = dayThreeSolution.secondPart(input);
        assertEquals("48", result);
    }
}