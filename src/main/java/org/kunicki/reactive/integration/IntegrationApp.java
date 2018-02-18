package org.kunicki.reactive.integration;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.JavaFlowSupport;
import org.kunicki.reactive.core.processor.MappingProcessor;
import org.kunicki.reactive.core.subscriber.PrintingSubscriber;
import reactor.adapter.JdkFlowAdapter;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class IntegrationApp {

    public static void main(String[] args) {
        Flux<Long> numberFlux = Flux.interval(Duration.ofSeconds(1));
        java.util.concurrent.Flow.Publisher<Long> reactorPublisher = JdkFlowAdapter.publisherToFlowPublisher(numberFlux);

        Flow<Long, Long, NotUsed> negatingFlow = Flow.of(Long.class).map(i -> -i);
        ActorMaterializer materializer = ActorMaterializer.create(ActorSystem.create());
        java.util.concurrent.Flow.Processor<Long, Long> akkaStreamsProcessor = JavaFlowSupport.Flow.toProcessor(negatingFlow).run(materializer);

        MappingProcessor<Long, Long> mappingProcessor = new MappingProcessor<>(i -> i + 5);

        reactorPublisher.subscribe(mappingProcessor);
        mappingProcessor.subscribe(akkaStreamsProcessor);
        akkaStreamsProcessor.subscribe(new PrintingSubscriber<>());
    }
}
