package com.playground.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlaygroudApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaygroudApplication.class, args);
	}
}
