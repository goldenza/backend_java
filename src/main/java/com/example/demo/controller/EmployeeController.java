package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
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
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee body) {
		
		return employeeRepository.save(body);
	}
	
	@GetMapping("/employee/{employeeId}")
	public Optional<Employee> getEmployeeDetail(@PathVariable Integer employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		return employee;
	}
	
	@PutMapping("/employee/{employeeId}")
	public Employee getEmployeeDetail(@PathVariable Integer employeeId, @RequestBody Employee body) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		if(employee.isPresent()) {
			employee.get().setFirstName(body.getFirstName());
			employee.get().setLastName(body.getLastName());
			employee.get().setSalary(body.getSalary());
			employee.get().setEmployeeId(body.getEmployeeId());
			
			employeeRepository.save(employee.get());
		
			return employee.get();
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId, @RequestBody Employee body) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		if(employee.isPresent()) {
			employeeRepository.delete(employee.get());
			return "DELETE SUCCESS";
		}else {
			return "Employee not found";
		}
	}
}
