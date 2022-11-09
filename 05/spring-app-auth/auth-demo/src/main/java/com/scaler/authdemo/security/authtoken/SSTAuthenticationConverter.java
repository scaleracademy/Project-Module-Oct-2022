package com.scaler.authdemo.security.authtoken;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class SSTAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        var token = request.getHeader("token");

        if (token != null) {
            return new SSTAuthentication(token);
        } else {
            return null;
        }
    }

}
