package com.scaler.blogapp.security.sst;

import com.scaler.blogapp.security.authtoken.AuthTokenService;
import com.scaler.blogapp.users.UsersEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

// (Sst) Server-side token -> authtoken
@Service("ServerSideToken")
public class SstService {
    private final AuthTokenService authTokenService;

    public SstService(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    public String createSst(UsersEntity user) {
        String username = user.getUsername();
        if(username == null || username.equals("")) {
            throw new IllegalArgumentException("Cannot create Server-side token with empty username");
        }

        return authTokenService.createToken(user);
    }

    public String getUsernameFromSst(String token) {
        return authTokenService.getUserFromToken(token).getUsername();
    }
}
