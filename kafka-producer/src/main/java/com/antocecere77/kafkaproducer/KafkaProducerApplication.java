package com.antocecere77.kafkaproducer;

import com.antocecere77.kafkaproducer.entity.Employee;
import com.antocecere77.kafkaproducer.entity.FoodOrder;
import com.antocecere77.kafkaproducer.entity.SimpleNumber;
import com.antocecere77.kafkaproducer.producer.*;
import com.antocecere77.kafkaproducer.service.ImageService;
import com.antocecere77.kafkaproducer.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@EnableScheduling
@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceProducer invoiceProducer;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 10; i++) {
			var invoice = invoiceService.generateInvoice();

			if(i>=5) {
				invoice.setAmount(-1);
			}

			invoiceProducer.sendMessage(invoice);
		} 
	}
}













