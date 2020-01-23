package com.dimed.service;

import com.dimed.pojo.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaProperties kafkaProperties;

    private Properties producerPropertiesConfig() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.bootstrapServer);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    private KafkaProducer<String, String> getProducer() {
        return new KafkaProducer<>(producerPropertiesConfig());
    }

    private ProducerRecord<String, String> getRecord(String message) {
        return new ProducerRecord<>(kafkaProperties.topic, message);
    }

    public void sendMessage(String message) {
        KafkaProducer<String, String> kafkaProducer = getProducer();
        kafkaProducer.send(getRecord(message));

        //Await accomulated records in produces will be send
        kafkaProducer.flush();
        kafkaProducer.close();

    }


}



//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);