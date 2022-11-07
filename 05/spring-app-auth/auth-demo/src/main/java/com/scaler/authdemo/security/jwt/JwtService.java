package com.scaler.authdemo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public static final String SECRET = "khb7k2jmn4b67k2jn35bl2j4bk5n2b6kn42";
    Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public String createJwt(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Cannot create JWT with blank username");
        }

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public String getUsernameFromJwt(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }
}
