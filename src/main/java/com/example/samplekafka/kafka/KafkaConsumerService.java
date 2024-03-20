package com.example.samplekafka.kafka;

import com.example.samplekafka.model.entity.Customer;
import com.example.samplekafka.service.CustomerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ReceiverOptions<String, Customer> kafkaReceiverOptions;

    private final CustomerService customerService;

    private final KafkaProducerService kafkaProducerService;

    @PostConstruct
    public void consume() {
        KafkaReceiver.create(kafkaReceiverOptions)
                .receive()
                .flatMap(message -> read(message))
                .flatMap(customer -> this.customerService.save(customer))
                .filter(item -> Objects.nonNull(item.getId()))
                .flatMap(kafkaProducerService::producer)
                .subscribe();
    }

    private Mono<Customer> read(ReceiverRecord<String, Customer> r) {
        Customer customer = r.value();
        log.info("Recebida a mensagem: {}", customer);
        r.receiverOffset().acknowledge();

        return Mono.just(customer);
    }

}
