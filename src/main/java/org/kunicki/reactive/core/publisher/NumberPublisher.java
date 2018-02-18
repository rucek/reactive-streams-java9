package org.kunicki.reactive.core.publisher;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class NumberPublisher implements Flow.Publisher<Integer> {

    private final SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher<>();

    public void start() {
        IntStream.iterate(1, i -> i+ 1).forEach(i -> {
            submissionPublisher.submit(i);
            sleep();
        });
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        submissionPublisher.subscribe(subscriber);
    }
}
