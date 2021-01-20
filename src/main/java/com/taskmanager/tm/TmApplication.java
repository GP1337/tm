package com.taskmanager.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmApplication {

	public static void main(String[] args) {

		try {SpringApplication.run(TmApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
