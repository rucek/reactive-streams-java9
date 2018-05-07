package org.kunicki.reactive.dyi;

import java.util.Iterator;
import java.util.concurrent.Flow;
import java.util.stream.IntStream;

public class SimplePublisher implements Flow.Publisher<Integer> {

    private final Iterator<Integer> iterator;

    SimplePublisher(int count) {
        this.iterator = IntStream.rangeClosed(1, count).iterator();
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {

    }
}
