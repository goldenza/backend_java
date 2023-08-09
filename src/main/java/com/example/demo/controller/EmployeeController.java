package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employee")
	public ResponseEntity<Object> getEmployee(){
		try {
			List<Employee> employees = employeeRepository.findAll();
			return new ResponseEntity<>(employees, HttpStatus.OK);
			
		} catch (Exception e){
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private List<Employee> data = new ArrayList<Employee>();
	
	@GetMapping("/employee")
	public List<Employee> getEmployee(){
		return data;
	}
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee body) {
		
		for(int i=0;i<data.size();i++) {
			if(data.get(i).getEmployeeId() == body.getEmployeeId())
				return null;
		}
		data.add(body);
		return body;
	}
	
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployeeDetail(@PathVariable Integer employeeId) {
		System.out.println("employeeId = "+employeeId);
		
		for(int i=0;i<data.size();i++) {
			if(employeeId == data.get(i).getEmployeeId())
				return data.get(i);
		}
		return null;
	}
	
	@PutMapping("/employee/{employeeId}")
	public Employee getEmployeeDetail(@PathVariable Integer employeeId, @RequestBody Employee body) {
		for(int i=0;i<data.size();i++) {
			if(employeeId == data.get(i).getEmployeeId())
				data.get(i).setFirstName(body.getFirstName());
				data.get(i).setLastName(body.getLastName());
				data.get(i).setSalary(body.getSalary());
				return data.get(i);
		}
		return null;
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId, @RequestBody Employee body) {
		for(int i=0;i<data.size();i++) {
			if(employeeId == data.get(i).getEmployeeId())
				return "delete successful";
		}
		return "employee not found";
	}
}
