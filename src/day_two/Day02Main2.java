package day_two;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class Day02Main2 {
    public static void main(String[] args) throws IOException {
        AtomicReference<Integer> result = new AtomicReference<>(0);

        int[] testValues = {18, 21, 23, 25, 28, 26, 27, 29}; // causes failure! -> rework entire logic

        // read input
        try (BufferedReader br = new BufferedReader(new FileReader("src/day_two/input.txt"))) {
            br.lines().forEach(line -> {
                String[] values = line.split(" ");
                System.out.println("values: " + Arrays.toString(values));

                Integer previousInteger = null;
                Boolean isDescending = null;
                boolean isSafe = true;
                boolean ignoredFirstUnsafe = false;

                for (String value : values) {
                    Integer intValue = Integer.parseInt(value);
                    if (previousInteger != null) {
                        final int diff = previousInteger - intValue;

                        final boolean wouldBeUnsafePath = wouldBeUnsafePath(diff, isDescending);
                        if (wouldBeUnsafePath) {
                            System.out.println("wouldBeUnsafePath: " + previousInteger + " " + intValue + " " + isDescending);
                            if (!ignoredFirstUnsafe) {
                                ignoredFirstUnsafe = true;
                                continue; // ignore this number and don't set it as previousValue
                            } else {
                                System.out.println("Completely unsafe, break op");
                                isSafe = false;
                                break;
                            }
                        }
                        if (isDescending == null) {
                            isDescending = diff > 0;
                        }
                    }

                    previousInteger = intValue;

                }

                if (isSafe) {
                    System.out.println("isSafe: " + Arrays.toString(values));
                    result.getAndUpdate(v -> v + 1);
                }
                System.out.println("End of line");
            });
        }

        System.out.println(result);
    }

    private static boolean wouldBeUnsafePath(int diff, Boolean isDescending) {
        return Math.abs(diff) > 3 ||
                diff == 0 ||
                isDescending != null &&
                        (isDescending && diff < 0 || // descend changes to ascend
                                (!isDescending && diff > 0)); // ascend changes to descend)
    }
}
