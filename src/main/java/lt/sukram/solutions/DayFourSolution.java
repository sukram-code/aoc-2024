package lt.sukram.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DayFourSolution implements DailySolution {

    private static final String XMAS = "XMAS";
    private static final String SAMX = "SAMX";
    private static final String MAS = "MAS";
    private static final String SAM = "SAM";

    @Override
    public String firstPart(List<String> input) {
        int result = 0;
        result += findInRows(input);
        result += findInColumns(input);
        result += findInDiagonals(input);
        return String.valueOf(result);
    }

    private int findInRows(List<String> lines) {
        List<String> validForMatching = lines.stream().filter(it -> it.length() >= XMAS.length()).toList();
        int total = 0;
        for (String line : validForMatching) {
            Matcher forwardMatcher = Pattern.compile(XMAS).matcher(line);
            Matcher backwardsMatcher = Pattern.compile(SAMX).matcher(line);
            while (forwardMatcher.find()) {
                total++;
            }
            while (backwardsMatcher.find()) {
                total++;
            }
        }
        return total;
    }

    private int findInColumns(List<String> lines) {
        List<String> columns = new ArrayList<>(lines.stream().map(_ -> "").toList());
        for (String line : lines) {
            for (int ch = 0; ch < lines.getFirst().length(); ch++) {
                columns.set(ch, columns.get(ch) + line.charAt(ch));
            }
        }
        return findInRows(columns);
    }

    private int findInDiagonals(List<String> lines) {
        int length = lines.getFirst().length();
        int height = lines.size();
        int countOfDiagonals = (height + length - 1) * 2;
        int lookingForDiagonalAtIndex = 0;
        List<String> diagonals = new ArrayList<>(IntStream.range(0, countOfDiagonals).mapToObj(_ -> "").toList());
        for (int col = 0; col < length; col++) {
            diagonals.set(lookingForDiagonalAtIndex, diagonals.get(lookingForDiagonalAtIndex) + getDiagonalDownRight(lines, 0, col));
            lookingForDiagonalAtIndex++;
        }
        for (int row = 1; row < height; row++) {
            diagonals.set(lookingForDiagonalAtIndex, diagonals.get(lookingForDiagonalAtIndex) + getDiagonalDownRight(lines, row, 0));
            lookingForDiagonalAtIndex++;
        }
        for (int col = length - 1; col >= 0; col--) {
            diagonals.set(lookingForDiagonalAtIndex, diagonals.get(lookingForDiagonalAtIndex) + getDiagonalDownLeft(lines, 0, col));
            lookingForDiagonalAtIndex++;
        }
        for (int row = 1; row < height; row++) {
            diagonals.set(lookingForDiagonalAtIndex, diagonals.get(lookingForDiagonalAtIndex) + getDiagonalDownLeft(lines, row, length - 1));
            lookingForDiagonalAtIndex++;
        }
        return findInRows(diagonals);
    }

    private String getDiagonalDownRight(List<String> lines, int startRow, int startCol) {
        int length = lines.getFirst().length();
        int height = lines.size();
        String diagonal = "";
        while (startRow < height && startCol < length) {
            diagonal = diagonal + lines.get(startRow).charAt(startCol);
            startRow++;
            startCol++;
        }
        return diagonal;
    }

    private String getDiagonalDownLeft(List<String> lines, int startRow, int startCol) {
        int height = lines.size();
        String diagonal = "";
        while (startRow < height && startCol >= 0) {
            diagonal = diagonal + lines.get(startRow).charAt(startCol);
            startRow++;
            startCol--;
        }
        return diagonal;
    }

    @Override
    public String secondPart(List<String> input) {
        int length = input.getFirst().length();
        int height = input.size();
        int numberOfXmas = (length - MAS.length() + 1) * (height - MAS.length() + 1);
        List<List<String>> possibleXmas = new ArrayList<>(IntStream.range(0, numberOfXmas).mapToObj(_ -> new ArrayList<>(List.of("", "", ""))).toList());
        int lookingForXmas = 0;
        for (int row = 0; row < height - 2; row++) {
            for (int col = 0; col < length - 2; col++) {
                List<String> xmas = possibleXmas.get(lookingForXmas);
                for (int i = 0; i < MAS.length(); i++) {
                    xmas.set(i, xmas.get(i) + input.get(row + i).charAt(col) + input.get(row + i).charAt(col + 1) + input.get(row + i).charAt(col + 2));
                }
                lookingForXmas++;
            }
        }
        int result = 0;
        for (List<String> xmas : possibleXmas) {
            String xRight = getDiagonalDownRight(xmas, 0, 0);
            String xLeft = getDiagonalDownLeft(xmas, 0, MAS.length() - 1);
            if (Stream.of(xRight, xLeft).allMatch(it -> it.equals(MAS) || it.equals(SAM))) {
                result++;
            }
        }
        return String.valueOf(result);
    }

    @Override
    public int day() {
        return 4;
    }
}
