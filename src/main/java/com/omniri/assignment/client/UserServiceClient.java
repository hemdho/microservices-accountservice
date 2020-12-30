package com.omniri.assignment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.omniri.assignment.dto.UserDto;

@FeignClient("USER-SERVICE")
public interface UserServiceClient {

	@PostMapping("/users")
	UserDto createUser(UserDto userDto);
}
