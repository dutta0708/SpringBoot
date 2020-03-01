package com.bank.details.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.details.dao.CustomerDetailsEntity;
import com.bank.details.exceptions.ApiException;
import com.bank.details.service.CustomerDetailsService;

@RestController
public class CustomerDetailsController {

	@Autowired
	private CustomerDetailsService service;

	@GetMapping("/details")
	public ResponseEntity<List<CustomerDetailsEntity>> getCustomerDetails() throws ApiException {

		List<CustomerDetailsEntity> list = service.getDetails();
		if (!list.isEmpty()) {
			return ResponseEntity.ok().body(list);
		} else {
			throw new ApiException();
		}
	}

	@GetMapping("/details/{custId}")
	public ResponseEntity<Optional<CustomerDetailsEntity>> getCustomerDetailsByID(@PathVariable String custId) {
		Optional<CustomerDetailsEntity> list = service.getDetailsByID(Integer.valueOf(custId));
		if (list.isPresent()) {
			return ResponseEntity.ok().body(list);
		} else {
			throw new ApiException();
		}
	}

	@PostMapping("/details")
	public ResponseEntity<String> addCustomerDetails(@RequestBody CustomerDetailsEntity details) {
		if (service.addCustomerDetails(details)) {
			return new ResponseEntity<>("Customer Added Successfully!!!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Please try again!!!", HttpStatus.OK);
	}

	@PutMapping("/details/customerID={custId}/initialCredit={ic}")
	public ResponseEntity<String> addAccountDetails(@PathVariable String custId, @PathVariable String ic) {
		if (service.addAccountDetails(Integer.valueOf(custId), Integer.valueOf(ic))) {
			return new ResponseEntity<>("Account Added Successfully!!!", HttpStatus.OK);
		}
		return new ResponseEntity<>("No Customer found with the given ID!!!", HttpStatus.OK);
	}

}
