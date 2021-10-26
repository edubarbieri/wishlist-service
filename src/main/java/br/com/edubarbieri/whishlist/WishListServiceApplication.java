package br.com.edubarbieri.whishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WishListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishListServiceApplication.class, args);
	}

}
