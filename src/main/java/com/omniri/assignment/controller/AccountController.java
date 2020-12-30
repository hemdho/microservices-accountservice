package com.omniri.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.omniri.assignment.bean.Account;
import com.omniri.assignment.client.UserServiceClient;
import com.omniri.assignment.dto.AccountDto;
import com.omniri.assignment.dto.AccountResponseDto;
import com.omniri.assignment.dto.GenericPageResponse;
import com.omniri.assignment.dto.UserDto;
import com.omniri.assignment.mapper.AccountMapper;
import com.omniri.assignment.service.IAccountService;

@CrossOrigin
@RestController
public class AccountController {

	@Autowired
	IAccountService accountService;
	
	@Autowired
	UserServiceClient userServiceClient;
	
	@Autowired
	AccountMapper accountMapper;
	
	@PostMapping("/accounts")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	public AccountResponseDto registerAccount(@RequestBody AccountDto accountDto) {
		UserDto userDto_=userServiceClient.createUser(accountDto.getUserDto());
		Account account =accountService.create(accountMapper.accountDtoToAccount(accountDto));
	   return accountMapper.accountToAccountDtoResponse(account);
	}
	
	@GetMapping("/accounts")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('BranchManager','Admin')")
	public GenericPageResponse<AccountResponseDto> getAllAccounts(@RequestParam int page, @RequestParam int limit){
		return accountMapper.pageToGenericPageResponse(accountService.findAll(page, limit));
		
	}
}
