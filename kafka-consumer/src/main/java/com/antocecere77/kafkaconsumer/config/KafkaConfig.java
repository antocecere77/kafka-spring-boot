package com.antocecere77.kafkaconsumer.config;

import com.antocecere77.kafkaconsumer.entity.CarLocation;
import com.antocecere77.kafkaconsumer.error.handler.GlobalErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        var properties = kafkaProperties.buildConsumerProperties();

        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

        return new DefaultKafkaConsumerFactory<Object, Object>(properties);
    }

    @Bean(name = "farLocationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {

        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        factory.setRecordFilterStrategy(new RecordFilterStrategy<Object, Object>() {

            ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
                try {
                    var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
                    return carLocation.getDistance() <= 100;
                } catch (JsonProcessingException e) {
                    return false;
                }
            }
        });

        return factory;
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {

        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        factory.setErrorHandler(new GlobalErrorHandler());

        return factory;
    }

    @Bean(name = "imageRetryContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {

        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        factory.setErrorHandler(new GlobalErrorHandler());
        factory.setRetryTemplate(createRetryTemplate());

        return factory;
    }

    private RetryTemplate createRetryTemplate() {

        var retryTemplate = new RetryTemplate();
        var retryPolicy = new SimpleRetryPolicy((3));
        retryTemplate.setRetryPolicy(retryPolicy);

        var backoffPolicy = new FixedBackOffPolicy();
        backoffPolicy.setBackOffPeriod(10_000);

        retryTemplate.setBackOffPolicy(backoffPolicy);
        return retryTemplate;
    }

}
