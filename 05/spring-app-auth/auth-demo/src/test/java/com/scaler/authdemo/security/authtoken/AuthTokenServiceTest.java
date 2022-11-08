package com.scaler.authdemo.security.authtoken;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class AuthTokenServiceTest {


    @Test
    void createToken() {

        AuthTokenService authTokenService = new AuthTokenService(null, null);
        String token = authTokenService.createToken(null);
        assertNull(token);
    }
}




