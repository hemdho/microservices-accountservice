package com.omniri.assignment.service;

import org.springframework.data.domain.Page;

import com.omniri.assignment.bean.Account;

public interface IAccountService extends CRUD<Account> {
    
	
	Page<Account> findAll(int page,int limit);
}
