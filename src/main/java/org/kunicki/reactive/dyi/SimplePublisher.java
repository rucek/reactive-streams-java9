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
        iterator.forEachRemaining(subscriber::onNext);
        subscriber.onComplete();
    }

    public static void main(String[] args) {
        new SimplePublisher(10).subscribe(new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {

            }

            @Override
            public void onNext(Integer item) {
                System.out.println("item = [" + item + "]");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable = [" + throwable + "]");
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        });
    }
}
