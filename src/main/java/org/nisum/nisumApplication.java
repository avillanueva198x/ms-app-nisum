package org.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal que inicia el microservicio
 * 
 * @author : Adolfo Villanueva
 * @version : 22/02/2023
 */
@SpringBootApplication
@EnableScheduling
public class nisumApplication {

	public static void main(String[] args) {
		SpringApplication.run(nisumApplication.class, args);
	}
}
