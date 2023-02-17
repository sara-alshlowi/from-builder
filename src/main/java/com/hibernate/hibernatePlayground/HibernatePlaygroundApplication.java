package com.hibernate.hibernatePlayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hibernate.hibernatePlayground"})
public class HibernatePlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatePlaygroundApplication.class, args);
	}

}
