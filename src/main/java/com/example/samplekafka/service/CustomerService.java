package com.example.samplekafka.service;

import com.example.samplekafka.model.entity.Customer;
import com.example.samplekafka.model.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> save(final Customer customer) {
        return this.customerRepository.save(customer);
    }
}
