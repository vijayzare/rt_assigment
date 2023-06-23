package com.rt.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Product {
		 private float id;
		 private String title;
		 private String description;
		 private float price;
		 private float discountPercentage;
		 private float rating;
		 private float stock;
		 private String brand;
		 private String category;
		 private String thumbnail;
		 private String[] images;



}
