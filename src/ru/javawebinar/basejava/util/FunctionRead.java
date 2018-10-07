package ru.javawebinar.basejava.util;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface FunctionRead<A, T extends Exception> {
    A get() throws T;

    static <A, T extends Exception> List<A> forEach(int size, FunctionRead<A, T> function) throws T {
        List<A> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(function.get());
        }
        return list;
    }
}
