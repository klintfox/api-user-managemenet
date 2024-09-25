package com.bci.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.dto.PhonesDto;
import com.bci.dto.ResponseDto;
import com.bci.dto.UserDto;
import com.bci.entity.Phone;
import com.bci.entity.User;
import com.bci.exception.ResourceBadRequestException;
import com.bci.exception.ResourceConflictException;
import com.bci.repository.PhoneRepository;
import com.bci.repository.UserRepository;
import com.bci.util.GeneralMessages;

@Service
public class UserServiceImpl implements UserService {

	private ResponseDto response;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public ResponseDto save(UserDto request) throws Exception {
        response = new ResponseDto();
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());
        validateUser(request);
        return response;
    }
    
    private void validateEmail(String email) throws ResourceBadRequestException {
        if (!UserValidationService.validateEmailPattern(email)) {
            throw new ResourceBadRequestException(GeneralMessages.ERROR_EMAIL_FORMAT);
        }
    }

    private void validatePassword(String password) throws ResourceBadRequestException {
        if (!UserValidationService.validatePasswordPattern(password)) {
            throw new ResourceBadRequestException(GeneralMessages.ERROR_PASSWORD_FORMAT + GeneralMessages.PASSWORD_FORMAT);
        }
    }

    private void validateUser(UserDto userDto) throws Exception {
        User userDb = userRepository.findByEmail(userDto.getEmail());
        if (userDb != null) {
            throw new ResourceConflictException(GeneralMessages.ERROR_USER_EXISTS);
        }
        saveUser(userDto);
    }

    private void saveUser(UserDto userDto) throws Exception {
        String token = TokenService.generateToken(userDto.getEmail());
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setIsactive(GeneralMessages.IS_ACTIVE);
        user.setToken(token);
        user = userRepository.save(user);
        
        if (user != null && userDto.getPhones() != null && !userDto.getPhones().isEmpty()) {
            savePhonesForUser(userDto, user);
        }
        
        sendResponse(user);
    }

    private void savePhonesForUser(UserDto userDto, User user) throws Exception {
        for (PhonesDto phoneDto : userDto.getPhones()) {
            Phone phone = new Phone();
            phone.setCitycode(phoneDto.getCitycode());
            phone.setCountrycode(phoneDto.getCountrycode());
            phone.setUser(user);
            phoneRepository.save(phone);
        }
    }

    private void sendResponse(User user) {
        response.setId(user.getId());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setIsactive(user.getIsactive());
        response.setMensaje(GeneralMessages.EXITOSO);
    }
        
}