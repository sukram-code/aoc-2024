package lt.sukram.solutions;

import lt.sukram.util.InputFileManager;
import lt.sukram.util.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaySixSolutionTest {

    private DailySolution daySixSolution;
    private List<String> input;

    @BeforeEach
    void setUp() {
        daySixSolution = new DaySixSolution();
        input = new InputProvider(new InputFileManager("src/test/resources/test-inputs", null)).getInputLines(6);
    }

    @Test
    void test_part1() {
        String result = daySixSolution.firstPart(input);
        assertEquals("41", result);
    }

    @Test
    void test_part2() {
        String result = daySixSolution.secondPart(input);
        assertEquals("41", result);
    }
}