package com.antocecere77.kafkaconsumer.consumer;

import com.antocecere77.kafkaconsumer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.http.HttpConnectTimeoutException;

@Service
public class ImageConsumer {

    private static final Logger log = LoggerFactory.getLogger(ImageConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_image", containerFactory = "imageRetryContainerFactory")
    public void consume(String message) throws JsonProcessingException, HttpConnectTimeoutException {
        var image = objectMapper.readValue(message, Image.class);

        if(image.getType().equals("svg")) {
            throw new HttpConnectTimeoutException("Simulate failed API call");
        }

        log.info("Processing image: {} ", image);
    }
}








