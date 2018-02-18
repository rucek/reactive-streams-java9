package org.kunicki.reactive.core.subscriber;

import org.kunicki.reactive.util.SimpleLogging;

import java.util.concurrent.Flow;

public abstract class SubscriberBase<T> implements Flow.Subscriber<T>, SimpleLogging {

    protected Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        info("onSubscribe");

        // https://github.com/reactive-streams/reactive-streams-jvm#2.5
        if (this.subscription == null) {
            this.subscription = subscription;
            this.subscription.request(1);
        } else {
            subscription.cancel();
        }
    }
}
