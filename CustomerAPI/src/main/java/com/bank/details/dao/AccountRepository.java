package com.bank.details.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetailsEntity, Integer>  {
	
	public List<AccountDetailsEntity> findByCustID(int custID);

}
