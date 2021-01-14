package ru.kislyakova.anastasia.channelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableEurekaClient
@SpringBootApplication
@EntityScan("ru.kislyakova.anastasia.channelmodel")
public class ChannelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChannelServiceApplication.class, args);
	}

}
