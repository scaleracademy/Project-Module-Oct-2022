package com.scaler.authdemo.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JwtAuthenticationFilter extends AuthenticationFilter {

    public JwtAuthenticationFilter(
            JwtAuthenticationManager authenticationManager
    ) {
        super(authenticationManager, new JwtAuthenticationConverter());

        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }


}
