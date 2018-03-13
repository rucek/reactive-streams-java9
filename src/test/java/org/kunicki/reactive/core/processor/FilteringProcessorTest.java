package org.kunicki.reactive.core.processor;

import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.IdentityFlowProcessorVerification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;

public class FilteringProcessorTest extends IdentityFlowProcessorVerification<Integer> {

  public FilteringProcessorTest() {
    super(new TestEnvironment());
  }

  @Override
  protected Flow.Publisher<Integer> createFailedFlowPublisher() {
    return null;
  }

  @Override
  protected Flow.Processor<Integer, Integer> createIdentityFlowProcessor(int bufferSize) {
    return new FilteringProcessor<>(integer -> true);
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
