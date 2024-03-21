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
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final ReceiverOptions<String, Customer> kafkaReceiverOptions;

    private final CustomerService customerService;

    private final KafkaProducerService kafkaProducerService;

    private AtomicInteger messagesReceived = new AtomicInteger(0);
    private long startTime = 0;
    private long endTime = 0;


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

        if (messagesReceived.get() == 0) {
            startTime = System.currentTimeMillis();
        }


        var customerSaved = this.customerService.save(customer);
        messagesReceived.incrementAndGet();
        // log.info("Recebida a mensagem: {}", customerSaved);
        r.receiverOffset().acknowledge();

        if (messagesReceived.get() == 500000) {
            endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Tempo total de consumo de " + messagesReceived.get() + " mensagens: " + (totalTime / 60000.0) + " ms");
        }

        return customerSaved;
    }

}
