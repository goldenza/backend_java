package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

@RestController
public class ProductController {
	
	private List<Product> data = new ArrayList<Product>();
	
	@GetMapping("/product")
	public List<Product> getProduct(){
		return data;
	}
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product body) {
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).getProductId() == body.getProductId())
				return null;
		}
		data.add(body);
		return body;
	}
	
	@GetMapping("/product/{productId}")
	public Product getProductDetail(@PathVariable Integer productId) {
		System.out.println("productId = " + productId);
		
		for(int i=0; i<data.size(); i++) {
			if(productId == data.get(i).getProductId())
				return data.get(i);
		}
		return null;
	}
	
	@PutMapping("/product/{productId}")
	public Product updateProductDetail(@PathVariable Integer productId, @RequestBody Product body) {
		for(int i=0; i<data.size(); i++) {
			if(productId == data.get(i).getProductId()) {
				data.get(i).setProductName(body.getProductName());
				data.get(i).setPrice(body.getPrice());
				data.get(i).setAmount(body.getAmount());
				data.get(i).setDetail(body.getDetail());
				return data.get(i);
			}
		}
		return null;
	}
	
	@DeleteMapping("/product/{productId}")
	public String deleteProduct(@PathVariable Integer productId) {
		for(int i=0; i<data.size(); i++) {
			if(productId == data.get(i).getProductId()) {
				data.remove(i);
				return "delete successful";
			}
		}
		return "product not found";
	}
}
