package ru.javawebinar.basejava.util;

import java.util.Collection;

@FunctionalInterface
public interface FunctionWrite<A, T extends Exception> {
    void accept(A a) throws T;

    static <A,  T extends Exception> void forEach(Collection<A> source,
                                                  FunctionWrite<A, T> function) throws T {
        for (A a : source) {
            function.accept(a);
        }

    }

    static <A, T extends Exception> void forEach(Collection<A> source,
                                                 FunctionWrite<A, T> function1,
                                                 FunctionWrite<A, T> function2) throws T {
        for (A a : source) {
            function1.accept(a);
            function2.accept(a);
        }
    }
}
