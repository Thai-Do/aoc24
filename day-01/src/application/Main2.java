package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Main2 {
    public static void main(String[] args) throws IOException {
        TreeMap<Integer, Integer> leftNumberToCountMap = new TreeMap<>();
        TreeMap<Integer, Integer> rightNumberToCountMap = new TreeMap<>();
        Integer result = 0;

        // read input
        try (BufferedReader br = new BufferedReader(new FileReader("day-01/src/application/input.txt"))) {
            br.lines().forEach(line -> {
                String[] values = line.split(" {3}");
                leftNumberToCountMap.merge(Integer.parseInt(values[0]), 1, Integer::sum);
                rightNumberToCountMap.merge(Integer.parseInt(values[1]), 1, Integer::sum);
            });
        }

        // calculate result
        for (Map.Entry<Integer, Integer> leftEntry : leftNumberToCountMap.entrySet()) {
            // (left key * counts on right side) * counts on left side, as each repetition yields the same value again
            result += leftEntry.getValue() * leftEntry.getKey() * Optional.ofNullable(rightNumberToCountMap.get(leftEntry.getKey())).orElse(0);
        }

        System.out.println(result);

    }
}
