package com.club.eliteclub;

import com.club.eliteclub.config.SwaggerConfig;
import com.club.eliteclub.initializers.Forbes400Properties;
import com.club.eliteclub.model.ClubDto;
import com.club.eliteclub.service.BillionairesService;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.club.eliteclub.service.EliteClubService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
@Import(SwaggerConfig.class)
@EnableConfigurationProperties(Forbes400Properties.class)
@SpringBootApplication
public class EliteClubApplication  {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);

	@Autowired
	private EliteClubService eliteClubService;
	@Autowired
	private BillionairesService billionairesService;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(300000))
				.setReadTimeout(Duration.ofMillis(300000))
				.build();

	}

		public static void main(String[] args) {
		SpringApplication.run(EliteClubApplication.class, args);

	}


}
