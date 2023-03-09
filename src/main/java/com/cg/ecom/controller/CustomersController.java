package com.cg.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ecom.dto.AddCustomersDTO;
import com.cg.ecom.dto.CustomersDTO;
import com.cg.ecom.exceptions.ItemNotAvailableException;
import com.cg.ecom.service.CustomersService;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	@Autowired
	public CustomersService customersService;

	

	@PostMapping("/addCustomer")
	public ResponseEntity<CustomersDTO> addCustomersnew(@RequestBody AddCustomersDTO customers) {

		CustomersDTO savecustomer = customersService.addCustomers(customers);
		return ResponseEntity.ok(savecustomer);

	}
	
	
	@PutMapping("/updateCustomers")
	public ResponseEntity<CustomersDTO> updateCustomers(@RequestBody CustomersDTO customersDTO) 
	{
		return new ResponseEntity<CustomersDTO>(customersService.updateCustomers(customersDTO), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteCustomers/{id}")
	public ResponseEntity<Boolean> deleteCustomersById(@PathVariable int id) {
		CustomersDTO customersDTO = customersService.getById(id);
		if (customersDTO != null) {
			customersService.deleteCustomers(customersDTO);
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		}
		throw new ItemNotAvailableException("Customers with id " + id + "doesnot exists");
	}
///////////////
	
	
	
}
