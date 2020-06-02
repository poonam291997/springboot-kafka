package com.example.retail.reader;

import com.example.retail.model.StockPrice;
import com.example.retail.writer.StockPriceDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class KafkaStockPriceReaderImpl implements KafkaStockPriceReader {

    private final KafkaConsumer<String, StockPrice> kafkaConsumer;


    public KafkaStockPriceReaderImpl(@Value("${kafka.topic.stock_price.name}") String topicName,
                                     @Value("${kafka.bootstrap.server}") String kafkaBootstrapServer,
                                     @Value("${stockPrice.consumer.group}") String consumerGroupId) {
        Properties kafkaConsumerProperties = new Properties();
        kafkaConsumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServer);
        kafkaConsumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaConsumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StockPriceDeserializer.class.getName());
        kafkaConsumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        kafkaConsumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        kafkaConsumerProperties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 15);
        kafkaConsumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        this.kafkaConsumer = new KafkaConsumer<>(kafkaConsumerProperties);
        this.kafkaConsumer.subscribe(List.of(topicName));
    }

    @Override
    public synchronized List<StockPrice> read() {
        List<StockPrice> stockPrices = new ArrayList<>();
        ConsumerRecords<String, StockPrice> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(2));
        for (ConsumerRecord<String, StockPrice> consumerRecord : consumerRecords) {
            stockPrices.add(consumerRecord.value());
        }
        return stockPrices;
    }
    public synchronized void commit() {
        this.kafkaConsumer.commitAsync((offsets, exception) -> {
            if (exception != null) {
            } else {
            }
        });
    }

    @PreDestroy
    public void cleanUp() {
        this.kafkaConsumer.close();
    }
}
