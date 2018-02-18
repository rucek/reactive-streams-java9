package org.kunicki.reactive.core.subscriber;

import org.kunicki.reactive.util.SimpleLogging;

public class PrintingSubscriber<T> extends SubscriberBase<T> implements SimpleLogging {

    @Override
    public void onNext(T item) {
        info("item: " + item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        error(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        info("onComplete");
    }
}
