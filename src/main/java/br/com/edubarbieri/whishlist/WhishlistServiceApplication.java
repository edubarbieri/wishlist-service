package br.com.edubarbieri.whishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WhishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhishlistServiceApplication.class, args);
	}

}
