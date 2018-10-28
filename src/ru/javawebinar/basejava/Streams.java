package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
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
        boolean oddOrEven = integers.stream().mapToInt(i -> i).sum() % 2 == 0;
        return integers.stream()
                .filter(x -> oddOrEven == (x % 2 != 0))
                .collect(Collectors.toList());
    }
}
