package com.bci.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.dto.PhonesDto;
import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;
import com.bci.entity.Phone;
import com.bci.entity.User;
import com.bci.exception.InvalidDataException;
import com.bci.exception.ResourceConflictException;
import com.bci.repository.PhoneRepository;
import com.bci.repository.UserRepository;
import com.bci.util.GeneralMessages;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private ResponseDto response;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public ResponseDto save(UserDto request) throws Exception {
		response = new ResponseDto();
		User userDb = userRepository.findByEmail(request.getEmail());
		if (userDb != null) {
			throw new ResourceConflictException(GeneralMessages.ERROR_USER_EXISTS);
		}
		if (hasPhones(request)) {
			saveUser(request);
		}
		return response;
	}

	private void saveUser(UserDto userDto) throws Exception {
		User user = new User();
		String token = TokenService.generateToken(userDto.getEmail());
		user = createUserEntity(userDto, token);
		userRepository.save(user);
		savePhonesForUser(userDto, user);
		sendResponse(user);
	}

	private void savePhonesForUser(UserDto userDto, User user) {
		userDto.getPhones().forEach(phoneDto -> {
			Phone phone = createPhoneEntity(phoneDto, user);
			phoneRepository.save(phone);
		});
	}

	private User createUserEntity(UserDto userDto, String token) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setCreated(new Date());
		user.setModified(new Date());
		user.setLastLogin(new Date());
		user.setIsactive(GeneralMessages.IS_ACTIVE);
		user.setToken(token);
		return user;
	}

	private Phone createPhoneEntity(PhonesDto phoneDto, User user) {
		Phone phone = new Phone();
		phone.setCitycode(phoneDto.getCitycode());
		phone.setCountrycode(phoneDto.getContrycode());
		phone.setUser(user);
		return phone;
	}

	private boolean hasPhones(UserDto userDto) {
		if (userDto.getPhones() == null && userDto.getPhones().isEmpty() && userDto.getPhones().size() > 0) {
			throw new InvalidDataException("El usuario debe tener al menos un teléfono.");
		}
		return true;
	}

	private void sendResponse(User user) {
		logger.info("All saved, sendResponse!");
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		response.setIsactive(user.getIsactive());
		response.setMensaje(GeneralMessages.EXITOSO);
	}

}