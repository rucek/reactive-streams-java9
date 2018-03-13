package org.kunicki.reactive.core.processor;

public class TakeProcessor<T> extends ProcessorBase<T, T> {

  private final long n;
  private volatile int counter = 0;

  public TakeProcessor(long n) {
    this.n = n;
  }

  @Override
  public void onNext(T item) {
    ++counter;
    if (counter < n) {
      submit(item);
      subscription.request(1);
    } else {
      onComplete();
      subscription.cancel();
    }
  }
}
