package org.kunicki.reactive.core;

import org.kunicki.reactive.core.processor.FilteringProcessor;
import org.kunicki.reactive.core.processor.MappingProcessor;
import org.kunicki.reactive.core.publisher.NumberPublisher;
import org.kunicki.reactive.core.subscriber.PrintingSubscriber;

public class CoreApp {

    public static void main(String[] args) {
        NumberPublisher numberPublisher = new NumberPublisher();
        MappingProcessor<Integer, Integer> mappingProcessor = new MappingProcessor<>(i -> i + 1);
        FilteringProcessor<Integer> filteringProcessor = new FilteringProcessor<>(i -> i % 2 == 0);
        PrintingSubscriber<Integer> printingSubscriber = new PrintingSubscriber<>();

        numberPublisher.subscribe(mappingProcessor);
        mappingProcessor.subscribe(filteringProcessor);
        filteringProcessor.subscribe(printingSubscriber);

        numberPublisher.start();
    }
}
