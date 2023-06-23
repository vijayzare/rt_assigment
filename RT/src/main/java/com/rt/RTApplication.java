package com.rt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@SpringBootApplication
@Data
public class RTApplication {

	  static KeyPair keyPair = null;
	  
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(RTApplication.class, args);
//	     keyPair = generateKeyPair();
//	     
//	     System.out.println("RSA Key Pair Generated:");
//	        System.out.println("Public Key: " + keyPair.getPublic());
//	        System.out.println("Private Key: " + keyPair.getPrivate());
	}



	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
