package com.dimed.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class KafkaProperties {

    @Value("${kafka.topic}")
    public String topic;

    @Value("${kafka.bootstrap.server}")
    public String bootstrapServer;
}
