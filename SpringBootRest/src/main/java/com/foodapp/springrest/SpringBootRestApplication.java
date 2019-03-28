package com.foodapp.springrest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootRestApplication {
	
	private static Logger LOGGER=LogManager.getLogger( SpringBootRestApplication.class);

	public static void main(String[] args) {
		LOGGER.info("enter into main method");
		SpringApplication.run(SpringBootRestApplication.class, args);
	
	}

}
