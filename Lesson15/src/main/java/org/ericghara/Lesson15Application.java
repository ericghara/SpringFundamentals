package org.ericghara;

import org.ericghara.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Lesson15Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson15Application.class, args);
	}
}
