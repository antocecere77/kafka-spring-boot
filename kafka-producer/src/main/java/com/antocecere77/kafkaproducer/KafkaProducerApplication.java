package com.antocecere77.kafkaproducer;

import com.antocecere77.kafkaproducer.producer.HelloKakfaProducer;
import com.antocecere77.kafkaproducer.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaKeyProducer kafkaKeyProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 10000; i++) {
			var key = "key-" + (i%4);
			var data = "data " + i + " with key " + key;
			kafkaKeyProducer.send(key, data);

			Thread.sleep(500);
		}
	}
}
