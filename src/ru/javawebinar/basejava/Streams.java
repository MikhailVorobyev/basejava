package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new int[]{9, 8}));
        System.out.println(minValue(new int[] {}));


        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }

    private static int minValue(int[] values) {
        return IntStream.of(values)
                .distinct()
                .sorted()
                .reduce((a, b) -> a * 10 + b)
                .orElse(-1);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> odd = new ArrayList<>();
        Map<Boolean, List<Integer>> result = integers.stream()
                .peek(integer -> {
                    if (integer % 2 != 0) {
                        odd.add(integer);
                    }
                })
                .collect(Collectors.partitioningBy(integer -> integer % 2 == 0));
        return result.get(odd.size() % 2 != 0);

    }
}
