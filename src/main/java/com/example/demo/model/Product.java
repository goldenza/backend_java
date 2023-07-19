package com.example.demo.model;

public class Product {
	private Integer productId;
	private String productName;
	private Integer price;
	private Double amount;
	private String detail;
	
	public Product() {
		super();
	}
	public Product(Integer productId, String productName, Integer price, Double amount, String detail) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.amount = amount;
		this.detail = detail;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
	
}
