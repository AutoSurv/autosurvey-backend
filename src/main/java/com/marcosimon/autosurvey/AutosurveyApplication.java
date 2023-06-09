package com.marcosimon.autosurvey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AutosurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosurveyApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(AutoSurveyService autosurveyService) {
		return args -> {
			//read json
			ObjectMapper mapper = new ObjectMapper();
			//TypeReference gives a reference of what type of data you want after the parsing is complete.
			TypeReference<List<AutoSurvey>> typeReference = new TypeReference<>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
			System.out.println("inputStream: " + inputStream);
			//write in db
			try {
				List<AutoSurvey> autoSurveys = mapper.readValue(inputStream, typeReference);
				autosurveyService.saveSurveys(autoSurveys);
				System.out.println("Surveys Saved");
			} catch (IOException e) {
				System.out.println("Error: " +e.getMessage());
			}
		};

	}

*/



}
