package com.dimed.kafka.spring;

import com.dimed.avro.Assinatura;
import com.dimed.kafka.Model.AssinaturaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer2 {

    @Autowired
    private KafkaTemplate<String, Assinatura> kafkaTemplate;

    public void sendMessage(Assinatura assinatura) {
        this.kafkaTemplate.send("test2",assinatura);
        log.info(String.format("Produced user -> %s", assinatura));
    }
}
