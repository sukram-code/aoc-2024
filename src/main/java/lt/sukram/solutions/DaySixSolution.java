package lt.sukram.solutions;

import java.util.Collection;
import java.util.List;

public class DaySixSolution implements DailySolution {

    private static final char VALID_STEP = '.';
    private static final char VALID_KNOWN_STEP = 'X';
    private static final char OBSTACLE = '#';
    private static final char GUARD = '^';

    @Override
    public String firstPart(List<String> input) {
        Guard guard = findGuard(input);
        while (guard.row() >= 0 && guard.row() < input.size() && guard.column() >= 0 && guard.column() < input.getFirst().length()) {
            guard = switch (guard.direction()) {
                case NORTH -> goNorth(guard, input);
                case EAST -> goEast(guard, input);
                case SOUTH -> goSouth(guard, input);
                case WEST -> goWest(guard, input);
            };
        }
        input.forEach(System.out::println);
        return String.valueOf(
                input.stream()
                        .map(it -> it.chars().mapToObj(c -> (char) c).toList())
                        .flatMap(Collection::stream)
                        .filter(it -> it.equals(VALID_KNOWN_STEP))
                        .count()
        );
    }

    private Guard goWest(Guard guard, List<String> input) {
        for (int col = guard.column(); col >= 0; col--) {
            String gridRow = input.get(guard.row());
            char step = gridRow.charAt(col);
            if (step == GUARD || step == VALID_STEP || step == VALID_KNOWN_STEP) {
                StringBuilder sb = new StringBuilder(gridRow);
                sb.setCharAt(col, VALID_KNOWN_STEP);
                input.set(guard.row(), sb.toString());
                return new Guard(guard.row(), col - 1, guard.direction());
            }
            if (step == OBSTACLE) {
                return new Guard(guard.row, col + 1, guard.direction().getDirectionToTurn());
            }
        }
        return new Guard(-1, -1, Direction.WEST);
    }

    private Guard goSouth(Guard guard, List<String> input) {
        for (int row = guard.row(); row < input.size(); row++) {
            String gridRow = input.get(row);
            char step = gridRow.charAt(guard.column());
            if (step == GUARD || step == VALID_STEP || step == VALID_KNOWN_STEP) {
                StringBuilder sb = new StringBuilder(gridRow);
                sb.setCharAt(guard.column(), VALID_KNOWN_STEP);
                input.set(row, sb.toString());
                return new Guard(row + 1, guard.column(), guard.direction());
            }
            if (step == OBSTACLE) {
                return new Guard(row - 1, guard.column(), guard.direction().getDirectionToTurn());
            }
        }
        return new Guard(-1, -1, Direction.SOUTH);
    }

    private Guard goEast(Guard guard, List<String> input) {
        for (int col = guard.column(); col < input.getFirst().length(); col++) {
            String gridRow = input.get(guard.row());
            char step = gridRow.charAt(col);
            if (step == GUARD || step == VALID_STEP || step == VALID_KNOWN_STEP) {
                StringBuilder sb = new StringBuilder(gridRow);
                sb.setCharAt(col, VALID_KNOWN_STEP);
                input.set(guard.row(), sb.toString());
                return new Guard(guard.row(), col + 1, guard.direction());
            }
            if (step == OBSTACLE) {
                return new Guard(guard.row, col - 1, guard.direction().getDirectionToTurn());
            }
        }
        return new Guard(-1, -1, Direction.EAST);
    }

    private Guard goNorth(Guard guard, List<String> input) {
        for (int row = guard.row(); row >= 0; row--) {
            String gridRow = input.get(row);
            char step = gridRow.charAt(guard.column());
            if (step == GUARD || step == VALID_STEP || step == VALID_KNOWN_STEP) {
                StringBuilder sb = new StringBuilder(gridRow);
                sb.setCharAt(guard.column(), VALID_KNOWN_STEP);
                input.set(row, sb.toString());
                return new Guard(row - 1, guard.column(), guard.direction());
            }
            if (step == OBSTACLE) {
                return new Guard(row + 1, guard.column(), guard.direction().getDirectionToTurn());
            }
        }
        return new Guard(-1, -1, Direction.NORTH);
    }

    private Guard findGuard(List<String> input) {
        for (int row = 0; row < input.size(); row++) {
            if (input.get(row).contains(String.valueOf(GUARD))) {
                return new Guard(row, input.get(row).indexOf(GUARD), Direction.NORTH);
            }
        }
        return new Guard(-1, -1, Direction.NORTH);
    }

    @Override
    public String secondPart(List<String> input) {
        return "";
    }

    @Override
    public int day() {
        return 6;
    }

    enum Direction {
        NORTH, EAST, WEST, SOUTH;

        public Direction getDirectionToTurn() {
            return switch (this) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    }

    record Guard(int row, int column, Direction direction) {}
}
