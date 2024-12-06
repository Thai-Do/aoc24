package day_three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03Main1 {
    public static void main(String[] args) throws IOException {
        AtomicInteger result = new AtomicInteger();
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
                    System.out.println("mult: " + firstFactor + " * " + secondFactor);
                    result.getAndAdd(firstFactor * secondFactor);
                });
            });
        }

        System.out.println(result);
    }

}
