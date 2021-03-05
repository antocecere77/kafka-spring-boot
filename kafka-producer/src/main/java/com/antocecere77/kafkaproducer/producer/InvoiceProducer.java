package com.antocecere77.kafkaproducer.producer;

import com.antocecere77.kafkaproducer.entity.Image;
import com.antocecere77.kafkaproducer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    @Autowired
    private KafkaTemplate<String , String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Invoice invoice) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(invoice);
        kafkaTemplate.send("t_invoice", invoice.getNumber(), json);
    }
}
