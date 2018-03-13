package org.kunicki.reactive.core.subscriber;

import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.FlowSubscriberBlackboxVerification;
import org.testng.annotations.Test;

import java.util.concurrent.Flow;

@Test
public class PrintingSubscriberTest extends FlowSubscriberBlackboxVerification<String> {

  public PrintingSubscriberTest() {
    super(new TestEnvironment());
  }

  @Override
  public Flow.Subscriber<String> createFlowSubscriber() {
    return new PrintingSubscriber<>();
  }

  @Override
  public String createElement(int i) {
    return "" + i;
  }
}
