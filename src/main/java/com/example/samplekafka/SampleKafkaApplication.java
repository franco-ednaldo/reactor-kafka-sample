package com.example.samplekafka;

import com.example.samplekafka.model.entity.Customer;
import com.example.samplekafka.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SampleKafkaApplication implements ApplicationRunner {
    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(SampleKafkaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        final var customer = new Customer(
//                "Ednaldo",
//                "Franco",
//                38,
//                "ednaldo@gmail.com",
//                "62 998395757");
//
//        final var customerSaved = this.customerService.save(customer);
//        System.out.println(customerSaved);
    }
}
