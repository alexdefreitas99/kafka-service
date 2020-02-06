package com.dimed.kafka;

import com.dimed.avro.Assinatura;
import com.dimed.kafka.Model.AssinaturaModel;
import com.dimed.kafka.Model.KafkaRequest;
import com.dimed.kafka.service.KafkaConsumerService;
import com.dimed.kafka.spring.KafkaProducer2;
import com.dimed.kafka.service.KafkaProducerService;
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

    @Autowired
    private KafkaProducer2 kafkaProducerService2;

    @PostMapping
    public String toProduceMessage(@RequestBody @Valid KafkaRequest message) throws Exception {
        kafkaProducerService.sendMessage(message);
        return "Message \n sent \n to \n kafka";
    }

    @PostMapping(path = "kafka2")
    public String toProduceMessage2(@RequestBody @Valid AssinaturaModel assinaturaModel) {
        kafkaProducerService2.sendMessage(new Assinatura(assinaturaModel.getIdAssinatura(), assinaturaModel.getHostname()));
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