package com.devhubsocial.microservices.core.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component("com.devhubsocial")
public class ContactServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ContactServiceApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ContactServiceApplication.class, args);
		String mysqlUri = ctx.getEnvironment().getProperty("spring.datasource.url");
		LOG.info("Connected to MySQL: " + mysqlUri);
	}

}
