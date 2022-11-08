package com.scaler.authdemo.security.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    JwtService jwtService = new JwtService();
    @DisplayName("Should create a JWT")
    @Test()
    void createJwt() {

        String jwt = jwtService.createJwt("test");
        assertNotNull(jwt);

    }
    @DisplayName("Should get username from JWT")
    @Test()
    void getUsernameFromJwt() {
        String username = jwtService.getUsernameFromJwt(jwtService.createJwt("test"));
        assertEquals("test", username);
    }

}