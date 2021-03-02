package com.antocecere77.kafkaconsumer.consumer;

import com.antocecere77.kafkaconsumer.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CommodityDashboardConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(CommodityDashboardConsumer.class);

    @KafkaListener(topics = "t_commodity", groupId = "cg-dashboard")
    public void consume(String message) throws JsonProcessingException, InterruptedException {
        var commodity = objectMapper.readValue(message, Commodity.class);
        log.info("Dashboard logic for {}", commodity);
    }

}
