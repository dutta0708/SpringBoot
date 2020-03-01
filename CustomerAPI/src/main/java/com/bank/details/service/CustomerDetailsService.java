package com.bank.details.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.details.dao.AccountDetailsEntity;
import com.bank.details.dao.AccountRepository;
import com.bank.details.dao.CustomerDetailsEntity;
import com.bank.details.dao.CustomerRepository;

@Service
public class CustomerDetailsService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private AccountRepository accRepo;

	public List<CustomerDetailsEntity> getDetails() {
		List<CustomerDetailsEntity> list = new ArrayList<>();
		custRepo.findAll().forEach(list::add);
		return list;
	}

	public boolean addCustomerDetails(CustomerDetailsEntity c) {
		if (custRepo.save(c) != null) {
			return true;
		}
		return false;
	}

	public Optional<CustomerDetailsEntity> getDetailsByID(int id) {
		return custRepo.findById(id);
	}

	public boolean addAccountDetails(int custId, int initCredit) {
		if (initCredit > 0 && custRepo.existsById(custId)) {
			AccountDetailsEntity entity = new AccountDetailsEntity();
			List<AccountDetailsEntity> newlist = new ArrayList<>();

			int sum = 0;
			int count = 1;

			entity.setTransaction(initCredit);
			entity.setCustID(custId);

			List<AccountDetailsEntity> oldlist = accRepo.findByCustID(custId);
			for (int i = 0; i < oldlist.size(); i++) {
				AccountDetailsEntity ae = oldlist.get(i);
				sum = sum + ae.getTransaction();
				count++;
			}
			entity.setAccountNo(count);
			newlist.add(entity);

			CustomerDetailsEntity details = custRepo.findById(custId).orElseThrow(RuntimeException::new);
			details.setAccountDetails(newlist);
			details.setBalance(sum + initCredit);
			details.setTransactions(count);

			custRepo.save(details);
			return true;
		} else {
			return false;
		}

	}

}
