package com.antocecere77.kafkaproducer;

import com.antocecere77.kafkaproducer.producer.HelloKakfaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private HelloKakfaProducer helloKakfaProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		helloKakfaProducer.sendHello("Antonio " + Math.random());
	}
}
