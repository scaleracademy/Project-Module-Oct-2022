package com.scaler.blogapp.security.sst;

import com.scaler.blogapp.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class sstAuthenticationManager implements AuthenticationManager {
    private SstService sstService;
    private UsersService usersService;

    public sstAuthenticationManager(SstService sstService, UsersService usersService) {
        this.sstService = sstService;
        this.usersService = usersService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof SstAuthentication) {
            SstAuthentication sstAuthentication = (SstAuthentication) authentication;

            var ssToken = sstAuthentication.getCredentials();
            var username = sstService.getUsernameFromSst(ssToken);

            var user = usersService.findUserByUsername(username);
            sstAuthentication.setUser(user);
            return sstAuthentication;
        }
        return null;
    }
}
