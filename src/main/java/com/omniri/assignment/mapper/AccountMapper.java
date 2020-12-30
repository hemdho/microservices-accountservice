package com.omniri.assignment.mapper;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.omniri.assignment.bean.Account;
import com.omniri.assignment.dto.AccountDto;
import com.omniri.assignment.dto.AccountResponseDto;
import com.omniri.assignment.dto.GenericPageResponse;
@Service
public class AccountMapper {

	
	public Account accountDtoToAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setAccountId(accountDto.getAccountId());
		account.setAccountType(accountDto.getAccountType());
		account.setBranch(accountDto.getBranch());
		account.setCustomerName(accountDto.getCustomerName());
		return account;
	}
	
	public AccountResponseDto accountToAccountDtoResponse(Account account) {
		AccountResponseDto accountResponseDto= new AccountResponseDto();
		accountResponseDto.setAccountId(account.getAccountId());
		accountResponseDto.setAccountType(account.getAccountType());
		accountResponseDto.setBranch(account.getBranch());
		accountResponseDto.setCustomerName(account.getCustomerName());
		accountResponseDto.setMinorIndicator(account.getMinorIndicator());
		accountResponseDto.setOpenDate(account.getOpenDate());
		return accountResponseDto;
	}
	
	public GenericPageResponse<AccountResponseDto> pageToGenericPageResponse(Page<Account> page){
		GenericPageResponse<AccountResponseDto> res=new GenericPageResponse<>();
		if(page.getTotalPages()>0) {
			res.setData(page.getContent().stream().map(account->accountToAccountDtoResponse(account)).collect(Collectors.toList()));
			res.setLimit(page.getSize());
			res.setPage(page.getNumber());
			res.setTotalPages(page.getTotalPages());
			res.setTotalRecords(page.getTotalElements());
			
		}
		
		return res;
	}
}
