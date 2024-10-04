package com.bci.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;
import com.bci.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Operation(summary = "User Register", description = "Return when user is save correct")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"),
			@ApiResponse(responseCode = "400", description = "BadRequest"),
			@ApiResponse(responseCode = "401", description = "UnauthorizedAccess"),
			@ApiResponse(responseCode = "404", description = "ResourceNotFound"),
			@ApiResponse(responseCode = "409", description = "ResourceConflict") })
	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody UserDto user) throws Exception {
		logger.info("Request{}: "+user.toString());
		ResponseDto response = new ResponseDto();
		response = userService.save(user);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
