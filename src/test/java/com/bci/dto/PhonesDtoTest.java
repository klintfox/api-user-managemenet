package com.bci.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class PhonesDtoTest {

    @Test
    public void testDefaultConstructor() {
        PhonesDto phonesDto = new PhonesDto();
        assertNull(phonesDto.getNumber());
        assertNull(phonesDto.getCitycode());
        assertNull(phonesDto.getContrycode());
    }

    @Test
    public void testParameterizedConstructor() {
        PhonesDto phonesDto = new PhonesDto("987654321", 2, 56);
        
        assertEquals("987654321", phonesDto.getNumber());
        assertEquals(2, phonesDto.getCitycode());
        assertEquals(56, phonesDto.getContrycode());
    }

    @Test
    public void testGettersAndSetters() {
        PhonesDto phonesDto = new PhonesDto();
        
        phonesDto.setNumber("123456789");
        assertEquals("123456789", phonesDto.getNumber());

        phonesDto.setCitycode(10);
        assertEquals(10, phonesDto.getCitycode());

        phonesDto.setContrycode(99);
        assertEquals(99, phonesDto.getContrycode());
    }
}