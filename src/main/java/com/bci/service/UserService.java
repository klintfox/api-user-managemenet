package com.bci.service;

import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;

public interface UserService {

	ResponseDto save(UserDto user) throws Exception;

}
