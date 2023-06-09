package com.marcosimon.autosurvey;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.autosurvey.AutoSurveyService;
import com.marcosimon.autosurvey.models.AutoSurveyDTO;
import com.marcosimon.autosurvey.models.AutoSurveyListDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AutosurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosurveyApplication.class, args);
	}



/*

	@Bean
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
				List<AutoSurvey> fieldListDto = mapper.readValue(inputStream, typeReference);
				autosurveyService.saveSurveys(fieldListDto.stream().map(autosurveyService::convertToDto).toList());
			} catch (IOException e) {
				System.out.println("Error: " +e.getMessage());
			}
		};

	}
*/


}
