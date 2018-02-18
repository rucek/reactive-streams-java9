package org.kunicki.reactive.core.processor;

import java.util.function.Predicate;

public class FilteringProcessor<T> extends ProcessorBase<T, T> {

    private final Predicate<T> predicate;

    public FilteringProcessor(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void onNext(T item) {
        if (predicate.test(item)) {
            submit(item);
        }

        subscription.request(1);
    }
}
