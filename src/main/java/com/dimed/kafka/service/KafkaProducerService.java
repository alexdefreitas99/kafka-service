package com.dimed.kafka.service;

import com.dimed.kafka.Model.AssinaturaModel;
import com.dimed.kafka.Model.KafkaMessageModel;
import com.dimed.kafka.Model.KafkaRequest;
import com.dimed.kafka.pojo.KafkaProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaProperties kafkaProperties;

    private Properties producerPropertiesConfig() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServer());
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    private KafkaProducer<String, String> getProducer() {
        return new KafkaProducer<>(producerPropertiesConfig());
    }

    private ProducerRecord<String, String> getRecord(String topic, KafkaMessageModel message) throws  Exception {
        return new ProducerRecord<>(topic, new ObjectMapper().writeValueAsString(message));
    }

    public void sendMessage(KafkaRequest message) throws Exception {
        KafkaProducer<String, String> kafkaProducer = getProducer();



        KafkaMessageModel kafkaMessageModel = KafkaMessageModel
                .builder()
                .filialTopic("filial_" + message.getFilial())
                .idAssinatura(message.getIdAssinatura())
                .hostname(message.getHostname())
                .itensAssinados(true)
                .build();

        kafkaProducer.send(getRecord(kafkaMessageModel.getFilialTopic(), kafkaMessageModel), messageCallback());
        kafkaProducer.close();
    }

    public Callback messageCallback() {
        return (metadata, exception) -> {
            if (exception == null) {
                log.info("Topic: " + metadata.topic() + "\n" +
                        "Offset: " + metadata.offset() + "\n" +
                        "Partition: " + metadata.partition() + "\n" +
                        "TimeStamp: " + metadata.timestamp());
            } else {
                log.error("Eror while producing message", exception);
            }
        };
    }


}
// More properties configuration

//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);