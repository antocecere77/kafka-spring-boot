package com.antocecere77.kafkaproducer;

import com.antocecere77.kafkaproducer.entity.Employee;
import com.antocecere77.kafkaproducer.entity.FoodOrder;
import com.antocecere77.kafkaproducer.entity.SimpleNumber;
import com.antocecere77.kafkaproducer.producer.*;
import com.antocecere77.kafkaproducer.service.ImageService;
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
	private ImageService imageService;

	@Autowired
	private ImageProducer imageProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var image1 = imageService.generateImage("jpg") ;
		var image2 = imageService.generateImage("svg") ;
		var image3 = imageService.generateImage("png") ;

		imageProducer.sendMessage(image1);
		imageProducer.sendMessage(image2);
		imageProducer.sendMessage(image3);
	}
}













