package com.antocecere77.kafkaproducer;

import com.antocecere77.kafkaproducer.entity.Employee;
import com.antocecere77.kafkaproducer.entity.FoodOrder;
import com.antocecere77.kafkaproducer.producer.EmployeeJsonProducer;
import com.antocecere77.kafkaproducer.producer.FoodOrderProducer;
import com.antocecere77.kafkaproducer.producer.HelloKakfaProducer;
import com.antocecere77.kafkaproducer.producer.KafkaKeyProducer;
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
	private FoodOrderProducer foodOrderProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var chickenOrder = new FoodOrder(3, "Chicken");
		var fishOrder = new FoodOrder(10, "Fish");
		var pizzaOrder = new FoodOrder(5, "Pizza");

		foodOrderProducer.send(chickenOrder);
		foodOrderProducer.send(fishOrder);
		foodOrderProducer.send(pizzaOrder);
	}
}
