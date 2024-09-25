package com.bci.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void testPhone() {
        Phone phone = new Phone();
        
        // Test setters
        phone.setId(1L);
        phone.setPhoneNumber("123456789");
        phone.setCitycode(1);
        phone.setCountrycode(1);
        
        // Test getters
        assertEquals(1L, phone.getId());
        assertEquals("123456789", phone.getPhoneNumber());
        assertEquals(1, phone.getCitycode());
        assertEquals(1, phone.getCountrycode());
        
        // Test setting and getting user
        User user = new User(); // Asegúrate de que User tenga un constructor y/o método de inicialización
        phone.setUser(user);
        assertEquals(user, phone.getUser());
    }
}