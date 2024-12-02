package day_two;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class Day02Main1 {
    public static void main(String[] args) throws IOException {
        AtomicReference<Integer> result = new AtomicReference<>(0);

        // read input
        try (BufferedReader br = new BufferedReader(new FileReader("src/day_two/input.txt"))) {
            br.lines().forEach(line -> {
                String[] values = line.split(" ");
                Integer previousInteger = null;
                Boolean isDescending = null;
                boolean isSafe = true;
                for (String value : values) {
                    Integer intValue = Integer.parseInt(value);
                    if (previousInteger != null) {
                        final int diff = previousInteger - intValue;

                        if (Math.abs(diff) > 3 ||
                                diff == 0 ||
                                isDescending != null &&
                                        (isDescending && diff < 0 || // descend changes to ascend
                                                (!isDescending && diff > 0))) // ascend changes to descend)
                        {
                            isSafe = false;
                            break;
                        }
                        if (isDescending == null) {
                            isDescending = diff > 0;
                        }
                    }
                    previousInteger = intValue;

                }
                if (isSafe) {
                    result.getAndUpdate(v -> v + 1);
                }
            });
        }

        System.out.println(result);
    }
}
