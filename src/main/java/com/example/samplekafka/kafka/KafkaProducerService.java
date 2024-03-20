package com.example.samplekafka.kafka;

import com.example.samplekafka.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
    private final String TOPIC  = "producer-topic";

    private final KafkaSender<String, Object> kafkaSender;

    public Mono<Void> producer(final Customer customer) {
        final var record = SenderRecord.create(new ProducerRecord<String, Object>(TOPIC, customer), 0);
        return this.kafkaSender.send(Mono.just(record))
                .doFirst(() -> log.info("Try to send message to {} with payload", TOPIC, customer))
                .onErrorResume(error -> Mono.error(new RuntimeException("Message didn't send", error)))
                .then();
    }

}
