package com.bci.dto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserDtoTest {

    @Test
    public void testDefaultConstructor() {
        UserDto userDto = new UserDto();
        assertNull(userDto.getName());
        assertNull(userDto.getEmail());
        assertNull(userDto.getPassword());
        assertNull(userDto.getPhones());
    }

    @Test
    public void testParameterizedConstructor() {
        List<PhonesDto> phones = new ArrayList<>();
        phones.add(new PhonesDto("987654321", 2, 56));

        UserDto userDto = new UserDto("Juan", "aaaaaa@dominio.cl", "Abcdef12", phones);
        
        assertEquals("Juan", userDto.getName());
        assertEquals("aaaaaa@dominio.cl", userDto.getEmail());
        assertEquals("Abcdef12", userDto.getPassword());
        assertNotNull(userDto.getPhones());
        assertEquals(1, userDto.getPhones().size());
        assertEquals("987654321", userDto.getPhones().get(0).getNumber());
    }

    @Test
    public void testGettersAndSetters() {
        UserDto userDto = new UserDto();
        
        userDto.setName("Pedro");
        assertEquals("Pedro", userDto.getName());

        userDto.setEmail("pedro@dominio.cl");
        assertEquals("pedro@dominio.cl", userDto.getEmail());

        userDto.setPassword("Password1");
        assertEquals("Password1", userDto.getPassword());

        List<PhonesDto> phones = new ArrayList<>();
        phones.add(new PhonesDto("123456789", 1, 55));
        userDto.setPhones(phones);
        assertNotNull(userDto.getPhones());
        assertEquals(1, userDto.getPhones().size());
        assertEquals("123456789", userDto.getPhones().get(0).getNumber());
    }
}
