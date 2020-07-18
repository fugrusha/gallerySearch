package com.golovko.gallerysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GallerySearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GallerySearchApplication.class, args);
	}

}
