package com.antocecere77.kafkaproducer.producer;

import com.antocecere77.kafkaproducer.entity.Employee;
import com.antocecere77.kafkaproducer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ImageProducer {

    @Autowired
    private KafkaTemplate<String , String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Image image) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(image);
        kafkaTemplate.send("t_image", image.getType(), json);
    }
}












