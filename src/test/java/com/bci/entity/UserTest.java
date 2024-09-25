package com.bci.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testGettersAndSetters() {
        UUID userId = UUID.randomUUID();
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "securePassword";
        Date createdDate = new Date();
        Date modifiedDate = new Date();
        Date lastLoginDate = new Date();
        String isActive = "true";
        String token = "sampleToken";

        user.setId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreated(createdDate);
        user.setModified(modifiedDate);
        user.setLastLogin(lastLoginDate);
        user.setIsactive(isActive);
        user.setToken(token);

        // Verificaciones
        assertEquals(userId, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(createdDate, user.getCreated());
        assertEquals(modifiedDate, user.getModified());
        assertEquals(lastLoginDate, user.getLastLogin());
        assertEquals(isActive, user.getIsactive());
        assertEquals(token, user.getToken());
    }

    @Test
    public void testPhoneAssociation() {
        List<Phone> phones = new ArrayList<>();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        phones.add(phone1);
        phones.add(phone2);
        
        user.setPhone(phones);
        
        assertEquals(2, user.getPhone().size());
        assertEquals(phone1, user.getPhone().get(0));
        assertEquals(phone2, user.getPhone().get(1));
    }

    @Test
    public void testEmptyPhoneList() {
        user.setPhone(new ArrayList<>());
        assertTrue(user.getPhone().isEmpty());
    }
}
