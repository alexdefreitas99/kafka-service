package com.dimed;

import com.dimed.service.KafkaConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaServiceApplication.class, args);
		KafkaConsumerService.runConsumer();
	}

}
