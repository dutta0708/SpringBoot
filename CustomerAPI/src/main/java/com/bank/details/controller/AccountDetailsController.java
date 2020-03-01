package com.bank.details.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.details.dao.AccountDetailsEntity;
import com.bank.details.service.AccountDetailsService;

@RestController
public class AccountDetailsController {
	
	@Autowired
	private AccountDetailsService accService;
	
	@GetMapping("/accDetails")
	public List<AccountDetailsEntity> getCustomerDetails() {
		return accService.getDetails();
	}
	
}
