package com.omniri.assignment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.omniri.assignment.bean.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, String> {

}
