package com.ekart.model;

public class Product {
	private int id;
	private String category;
	private String name;
	private int price;
	private String image;
	
	public Product() {
		super();
	}
	public Product(int id, String category, String name, int price, String image) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	

}
