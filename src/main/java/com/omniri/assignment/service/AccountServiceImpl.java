package com.omniri.assignment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.omniri.assignment.bean.Account;
import com.omniri.assignment.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	AccountRepository accountRepo;

	@Override
	public Account create(Account account) {
		account.setAccountId(UUID.randomUUID().toString());
		return accountRepo.save(account);
		
	}

	
	@Override
	public Account delete(Account t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<Account> findAll(int page,int limit) {
		Pageable p= PageRequest.of(page-1, limit);		
		Page<Account> accounts=	accountRepo.findAll(p);
		
		return accounts;
	}

}
