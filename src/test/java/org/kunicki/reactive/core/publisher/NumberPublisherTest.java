package org.kunicki.reactive.core.publisher;

import org.kunicki.reactive.core.processor.TakeProcessor;
import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.FlowPublisherVerification;
import org.testng.annotations.Test;

import java.util.concurrent.Flow;

@Test
public class NumberPublisherTest extends FlowPublisherVerification<Integer> {

  public NumberPublisherTest() {
    super(new TestEnvironment());
  }

  @Override
  public Flow.Publisher<Integer> createFlowPublisher(long l) {
    NumberPublisher numberPublisher = new NumberPublisher();
    TakeProcessor<Integer> processor = new TakeProcessor<>(l);
    numberPublisher.subscribe(processor);

    numberPublisher.start();

    return processor;
  }

  @Override
  public Flow.Publisher<Integer> createFailedFlowPublisher() {
    return null;
  }
}
