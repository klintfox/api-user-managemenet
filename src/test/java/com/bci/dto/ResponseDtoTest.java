package com.bci.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class ResponseDtoTest {

    @Test
    public void testDefaultConstructor() {
        ResponseDto responseDto = new ResponseDto();
        assertNull(responseDto.getMensaje());
        assertNull(responseDto.getId());
        assertNull(responseDto.getCreated());
        assertNull(responseDto.getModified());
        assertNull(responseDto.getLastLogin());
        assertNull(responseDto.getToken());
        assertNull(responseDto.getIsactive());
    }

    @Test
    public void testParameterizedConstructor() {
        UUID id = UUID.randomUUID();
        String mensaje = "Success";
        Date created = new Date();
        Date modified = new Date();
        Date lastLogin = new Date();
        String token = "sampleToken";
        String isactive = "true";

        ResponseDto responseDto = new ResponseDto(mensaje, id, created, modified, lastLogin, token, isactive);

        assertEquals(mensaje, responseDto.getMensaje());
        assertEquals(id, responseDto.getId());
        assertEquals(created, responseDto.getCreated());
        assertEquals(modified, responseDto.getModified());
        assertEquals(lastLogin, responseDto.getLastLogin());
        assertEquals(token, responseDto.getToken());
        assertEquals(isactive, responseDto.getIsactive());
    }

    @Test
    public void testSingleParameterConstructor() {
        String mensaje = "Error occurred";
        ResponseDto responseDto = new ResponseDto(mensaje);
        assertEquals(mensaje, responseDto.getMensaje());
        assertNull(responseDto.getId());
        assertNull(responseDto.getCreated());
        assertNull(responseDto.getModified());
        assertNull(responseDto.getLastLogin());
        assertNull(responseDto.getToken());
        assertNull(responseDto.getIsactive());
    }

    @Test
    public void testGettersAndSetters() {
        ResponseDto responseDto = new ResponseDto();
        
        responseDto.setMensaje("Test message");
        assertEquals("Test message", responseDto.getMensaje());

        UUID id = UUID.randomUUID();
        responseDto.setId(id);
        assertEquals(id, responseDto.getId());

        Date created = new Date();
        responseDto.setCreated(created);
        assertEquals(created, responseDto.getCreated());

        Date modified = new Date();
        responseDto.setModified(modified);
        assertEquals(modified, responseDto.getModified());

        Date lastLogin = new Date();
        responseDto.setLastLogin(lastLogin);
        assertEquals(lastLogin, responseDto.getLastLogin());

        responseDto.setToken("tokenValue");
        assertEquals("tokenValue", responseDto.getToken());

        responseDto.setIsactive("true");
        assertEquals("true", responseDto.getIsactive());
    }

    @Test
    public void testToString() {
        UUID id = UUID.randomUUID();
        ResponseDto responseDto = new ResponseDto("Test message", id, new Date(), new Date(), new Date(), "token", "true");
        String expectedString = "ResponseDto [mensaje=Test message, id=" + id + ", created=" + responseDto.getCreated() + 
                                ", modified=" + responseDto.getModified() + ", lastLogin=" + responseDto.getLastLogin() + 
                                ", token=token, isactive=true]";
        assertEquals(expectedString, responseDto.toString());
    }
}