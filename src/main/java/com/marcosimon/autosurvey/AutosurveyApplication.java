package com.marcosimon.autosurvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AutosurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosurveyApplication.class, args);
	}

}
