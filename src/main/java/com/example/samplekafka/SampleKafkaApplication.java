package com.example.samplekafka;

import com.example.samplekafka.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SampleKafkaApplication {
    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(SampleKafkaApplication.class, args);
    }


}
