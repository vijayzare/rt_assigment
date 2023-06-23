package com.rt.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rt.dto.Product;
import com.rt.dto.UserDetails;
import com.rt.dto.ZipCode;

@RestController
@RequestMapping("/api")
public class MyController {
	private final RestTemplate restTemplate;

	@Autowired
	public MyController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// Zippopotam
	// Get information about a specified ZIP code.
	@GetMapping("/zip/{zipCode}")
	public ZipCode getCurrentIp(@PathVariable Long zipCode) {
		String url = "https://api.zippopotam.us/us/" + zipCode;
		ZipCode zipCodeObj = restTemplate.getForObject(url, ZipCode.class);
		return zipCodeObj;

	}

	// Genderize.io
	// Predict the gender of a person based on their name.
	@GetMapping("/userDetails/{name}")
	public UserDetails getUsersGender(@PathVariable String name) {

		String url = "https://api.genderize.io?name=" + name;
		UserDetails userDetails = restTemplate.getForObject(url, UserDetails.class);
		return userDetails;
	}

	
	//get Specific Product
	@GetMapping("/getProduct")
	public Product getProducts() {

		String url = "https://dummyjson.com/products/1";
		Product product = restTemplate.getForObject(url, Product.class);
		return product;
	}

}
