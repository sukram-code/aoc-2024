package lt.sukram.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DayFiveSolution implements DailySolution {
    @Override
    public String firstPart(List<String> input) {
        Map<String, Set<String>> orderedRules = rulesInOrder(input.stream().filter(it -> it.contains("|")).toList());
        List<String> updates = input.stream().filter(it -> it.contains(",")).toList();
        List<String> validUpdates = updates.stream().filter(it -> validUpdate(it.split(","), orderedRules)).toList();
        return String.valueOf(getResult(validUpdates));
    }

    private boolean validUpdate(String[] instructions, Map<String, Set<String>> orderedRules) {
        String previousInstruction = "";
        for (String instruction : instructions) {
            if (previousInstruction.isEmpty() || orderedRules.get(previousInstruction).contains(instruction)) {
                previousInstruction = instruction;
            } else {
                return false;
            }
        }
        return true;
    }

    private Map<String, Set<String>> rulesInOrder(List<String> rulesToOrder) {
        Map<String, Set<String>> leaderToFollowers = rulesToOrder.stream()
                .map(it -> Arrays.asList(it.split("\\|")))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(it -> it, _ -> new HashSet<>()));
        List<Rule> rules = rulesToOrder.stream()
                .map(it -> {
                    String[] split = it.split("\\|");
                    return new Rule(split[0], split[1]);
                }).toList();
        for (Rule rule : rules) {
            String leader = rule.leading();
            leaderToFollowers.get(leader).add(rule.trailing());
        }
        return leaderToFollowers;
    }

    @Override
    public String secondPart(List<String> input) {
        Map<String, Set<String>> orderedRules = rulesInOrder(input.stream().filter(it -> it.contains("|")).toList());
        List<String> updates = input.stream().filter(it -> it.contains(",")).toList();
        List<String> invalidUpdates = updates.stream().filter(it -> !validUpdate(it.split(","), orderedRules)).toList();
        List<String> validUpdates = invalidUpdates.stream().map(it -> makeItValid(it.split(","), orderedRules)).toList();
        return String.valueOf(getResult(validUpdates));
    }

    private String makeItValid(String[] invalidUpdate, Map<String, Set<String>> orderedRules) {
        List<String> validOrder = new ArrayList<>(IntStream.range(0, invalidUpdate.length).mapToObj(_ -> "").toList());
        for (String instruction : invalidUpdate) {
            int trailingInstructions = (int) orderedRules.get(instruction).stream().filter(it -> Arrays.asList(invalidUpdate).contains(it)).count();
            validOrder.set(validOrder.size() - 1 - trailingInstructions, instruction);
        }
        return String.join(",", validOrder);
    }

    private Integer getResult(List<String> validUpdates) {
        return validUpdates.stream()
                .map(it -> {
                    String[] instructions = it.split(",");
                    return instructions[instructions.length / 2];
                })
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    @Override
    public int day() {
        return 5;
    }

    record Rule(String leading, String trailing) {
    }
}
