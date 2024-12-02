package day_one;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day01Main1 {
    public static void main(String[] args) throws IOException {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        Integer result = 0;

        // read input
        try (BufferedReader br = new BufferedReader(new FileReader("src/day_one/input.txt"))) {
            br.lines().forEach(line -> {
                String[] values = line.split(" {3}");
                leftList.add(Integer.parseInt(values[0]));
                rightList.add(Integer.parseInt(values[1]));
            });
        }

        // sort
        List<Integer> leftListSorted = leftList.stream().sorted().toList();
        List<Integer> rightListSorted = rightList.stream().sorted().toList();
        System.out.println(leftListSorted);
        System.out.println(rightListSorted);

        // calculate result
        for (int i = 0; i < leftList.size(); i++) {
            result += Math.abs(leftListSorted.get(i) - rightListSorted.get(i));
        }

        System.out.println(result);

    }
}
