package org.kunicki.reactive.core.processor;

import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.IdentityFlowProcessorVerification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class TakeProcessorTest extends IdentityFlowProcessorVerification<Integer> {

  public TakeProcessorTest() {
    super(new TestEnvironment());
  }

  @Override
  protected Flow.Publisher<Integer> createFailedFlowPublisher() {
    return new Flow.Publisher<Integer>() {
      @Override
      public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(new Flow.Subscription() {
          @Override
          public void request(long n) {
          }

          @Override
          public void cancel() {
          }
        });
        subscriber.onError(new Exception(""));
      }
    };
  }

  @Override
  protected Flow.Processor<Integer, Integer> createIdentityFlowProcessor(int bufferSize) {
    return new TakeProcessor<>(bufferSize);
  }

  @Override
  public ExecutorService publisherExecutorService() {
    return Executors.newFixedThreadPool(4);
  }

  @Override
  public Integer createElement(int i) {
    return i;
  }
}
