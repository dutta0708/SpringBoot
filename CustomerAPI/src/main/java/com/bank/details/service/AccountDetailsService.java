package com.bank.details.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.details.dao.AccountDetailsEntity;
import com.bank.details.dao.AccountRepository;

@Service
public class AccountDetailsService {

	@Autowired
	private AccountRepository accRepo;

	public List<AccountDetailsEntity> getDetails() {
		List<AccountDetailsEntity> list = new ArrayList<>();
		accRepo.findAll().forEach(list::add);
		return list;
	}

}
