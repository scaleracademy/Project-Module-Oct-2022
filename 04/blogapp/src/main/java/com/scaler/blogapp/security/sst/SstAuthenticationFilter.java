package com.scaler.blogapp.security.sst;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class SstAuthenticationFilter extends AuthenticationFilter {
    public SstAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager, new SstAuthenticationConverter());

        this.setSuccessHandler(((request, response, authentication) ->
                SecurityContextHolder.getContext().setAuthentication(authentication))
        );
    }
}
