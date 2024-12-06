package day_three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03Main2 {
    public static void main(String[] args) throws IOException {
        AtomicInteger result = new AtomicInteger();
        Map<Integer, Integer> startIndexToMulMap = new TreeMap<>();
        AtomicBoolean mulActive = new AtomicBoolean(true);

        try (BufferedReader br = new BufferedReader(new FileReader("src/day_three/input.txt"))) {
            br.lines().forEach(line -> {
                System.out.println(line);
                Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
                Matcher matcher = pattern.matcher(line);
                matcher.results().forEach(match -> {
                    String matchString = line.substring(match.start(), match.end());
                    String[] mulNumbers = matchString.split(",");
                    final int firstFactor = Integer.parseInt(mulNumbers[0].substring("mul(".length()));
                    final int secondFactor = Integer.parseInt(mulNumbers[1].substring(0, mulNumbers[1].length() - 1));
                    startIndexToMulMap.put(match.start(), firstFactor * secondFactor);
                });


                Pattern doPattern = Pattern.compile("do\\(\\)");
                Matcher doMatcher = doPattern.matcher(line);
                doMatcher.results().forEach(matchResult -> startIndexToMulMap.put(matchResult.start(), 1));

                Pattern dontPattern = Pattern.compile("don't\\(\\)");
                Matcher dontMatcher = dontPattern.matcher(line);
                dontMatcher.results().forEach(matchResult -> startIndexToMulMap.put(matchResult.start(), 0));

                startIndexToMulMap.forEach((key, value) -> {
                    if (value.equals(1)) {
                        mulActive.set(true);
                    } else if (value.equals(0)) {
                        mulActive.set(false);
                    } else {
                        if (mulActive.get()) {
                            result.getAndAdd(value);
                        }
                    }
                });

                startIndexToMulMap.clear();
            });
        }

        System.out.println(result);
    }

}
