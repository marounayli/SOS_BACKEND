package com.sosesib.backend.functional;

@FunctionalInterface
public interface Aggregator<X extends Number> {
    X process(X arg1, X arg2);
}
