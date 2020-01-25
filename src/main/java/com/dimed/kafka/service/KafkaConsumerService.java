package com.dimed.kafka.service;

import com.dimed.kafka.pojo.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Service
public class KafkaConsumerService {

    @Autowired
    private KafkaProperties kafkaProperties;

    private Properties consumerPropertiesConfig() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumerGroup());
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServer());
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return props;
    }

    public void runConsumer() {
        KafkaConsumer consumer = new KafkaConsumer(consumerPropertiesConfig());
        consumer.subscribe(Collections.singletonList("first_topic"));

        final int giveUp = 100;
        int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);

            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }


    public ConsumerRecords<String, String> getAllMessages(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer(consumerPropertiesConfig());
        TopicPartition topicPartition = new TopicPartition(topic, 0);
        List<TopicPartition> partitions = Collections.singletonList(topicPartition);
        consumer.assign(partitions);


        consumer.poll(0);
        consumer.seekToBeginning(consumer.assignment());
        ConsumerRecords<String, String> records = consumer.poll(0);
        return records;
    }

}
