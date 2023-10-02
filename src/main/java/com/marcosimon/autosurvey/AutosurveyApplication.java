package com.marcosimon.autosurvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackages = {"com.marcosimon.autosurvey.autosurvey",
		"com.marcosimon.autosurvey.organization", "com.marcosimon.autosurvey.user"})
public class AutosurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosurveyApplication.class, args);
	}

}
