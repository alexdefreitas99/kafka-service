package com.dimed.kafka.pojo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KafkaProperties {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.bootstrap.server}")
    private String bootstrapServer;

    @Value("${kafka.consumer.group}")
    private String consumerGroup;
}
