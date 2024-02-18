package com.example.samplekafka.kafka;

import com.example.samplekafka.model.entity.Customer;
import com.example.samplekafka.service.CustomerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ReceiverOptions<String, Customer> kafkaReceiverOptions;

    private final CustomerService customerService;

    @PostConstruct
    public void consume() {
        KafkaReceiver.create(kafkaReceiverOptions)
                .receive()
                .flatMap(message -> read(message))
                .flatMap(customer -> this.customerService.save(customer))
                .subscribe();
    }

    private Mono<Customer> read(ReceiverRecord<String, Customer> r) {
        Customer customer = r.value();
        System.out.println("Recebida a mensagem: " + customer.getFirsName() + ", " +
                customer.getAge() + ", " +
                customer.getEmail() + ", " +
                customer.getPhoneNumber() + ", " + LocalDateTime.now());
        r.receiverOffset().acknowledge();

        return Mono.just(customer);
    }

}
