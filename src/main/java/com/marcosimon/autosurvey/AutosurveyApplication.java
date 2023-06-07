package com.marcosimon.autosurvey;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	CommandLineRunner runner(MilkService milkService) {
		return args -> {
			//read json
			ObjectMapper mapper = new ObjectMapper();
			//TypeReference gives a reference of what type of data you want after the parsing is complete.
			TypeReference<MilkListFileDTO> typeReference = new TypeReference<MilkListFileDTO>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/milk.json");
			System.out.println("inputStream: " + inputStream);
			//write in db
			try {
				MilkListFileDTO milkListFileDTO = mapper.readValue(inputStream, typeReference);
				List<MilkFileDTO> milkFileDtoList = milkListFileDTO.results();
				List<Milk> milks =new ArrayList<>();
				for (MilkFileDTO milkFileDTO : milkFileDtoList) {
					milks.add(new Milk(milkFileDTO.name(), milkFileDTO.type(), milkFileDTO.storage()));
				}
				milkService.saveAll(milks);
			} catch (IOException e) {
				System.out.println("Error: " +e.getMessage());
			}
		};

	}
	*/
}
