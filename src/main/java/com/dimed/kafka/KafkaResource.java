package com.dimed.kafka;

import com.dimed.kafka.service.KafkaConsumerService;
import com.dimed.kafka.service.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/kafka", produces = MediaType.APPLICATION_JSON_VALUE)
public class KafkaResource {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public String toProduceMessage(@RequestBody @Valid Object customer, @RequestParam int times) throws JsonProcessingException {
        kafkaProducerService.sendMessage(new ObjectMapper().writeValueAsString(customer), times);
        return "Message \n sent \n to \n kafka";
    }

    @GetMapping(path = "/run-consumer-from-begin/{topic}")
    public String runConsumerFromBegin(@PathVariable String topic) {
        kafkaConsumerService.runConsumerFromBegin(topic);
        return "Consumer is running !!";
    }

    @GetMapping(path = "/run-consumer/{topic}")
    public String runConsumer(@PathVariable String topic) {
        kafkaConsumerService.runConsumer(topic);
        return "Consumer is running !!";
    }
}