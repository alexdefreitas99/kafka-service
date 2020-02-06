package com.dimed.kafka.spring;

import com.dimed.avro.Assinatura;
import com.dimed.kafka.Model.AssinaturaModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListenerService {

    @KafkaListener(topics = "test2", groupId = "group_id")
    public void consume(ConsumerRecord<String, Assinatura> record) {
        log.info(String.format("Consumed message -> %s", record.value()));
    }
}
