package ru.javawebinar.basejava;

import java.util.stream.IntStream;

public class Streams {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new int[]{9, 8}));
    }

    private static int minValue(int[] values) {
        return IntStream.of(values)
                .distinct()
                .sorted()
                .reduce((a, b) -> a * 10 + b)
                .getAsInt();
    }
}
