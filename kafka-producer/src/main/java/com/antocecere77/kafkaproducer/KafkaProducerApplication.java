package com.antocecere77.kafkaproducer;

import com.antocecere77.kafkaproducer.entity.Employee;
import com.antocecere77.kafkaproducer.producer.EmployeeJsonProducer;
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
	private EmployeeJsonProducer employeeJsonProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		for (int i = 0; i < 5; i++) {
//			var employee = new Employee("emp-" + 1, "Employee " + i, LocalDate.now());
//			employeeJsonProducer.sendMessage(employee);
//		}
	}
}
