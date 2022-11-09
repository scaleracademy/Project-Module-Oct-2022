package com.scaler.authdemo.security;

import com.scaler.authdemo.security.authtoken.AuthTokenService;
import com.scaler.authdemo.security.authtoken.SSTAuthenticationFilter;
import com.scaler.authdemo.security.authtoken.SSTAuthenticationManager;
import com.scaler.authdemo.security.jwt.JwtAuthenticationFilter;
import com.scaler.authdemo.security.jwt.JwtAuthenticationManager;
import com.scaler.authdemo.security.jwt.JwtService;
import com.scaler.authdemo.users.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    //private JwtAuthenticationFilter jwtAuthenticationFilter;
    private SSTAuthenticationFilter sstAuthenticationFilter;

    /* ASSIGNMENT 06:

        Implement server-side token authentication as well, and make
        both types of auth available in your server
     */

    public AppSecurityConfig(
           AuthTokenService authTokenService
    ) {
        sstAuthenticationFilter = new SSTAuthenticationFilter(
                new SSTAuthenticationManager(authTokenService)
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: in prod, CORS and CSRF shouldn't be blanket disabled
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/about").permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                .antMatchers("/*/**").authenticated()
                .and()
                //.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .addFilterBefore(sstAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        super.configure(http);
    }
}
