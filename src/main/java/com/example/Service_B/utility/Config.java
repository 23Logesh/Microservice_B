package com.example.Service_B.utility;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	
	private ModelMapper modelMapper;
	
	private RestTemplate restTemplate;


	
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public RestTemplate geTemplate() {
		return new RestTemplate();
	}


}
